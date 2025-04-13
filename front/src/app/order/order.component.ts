import {Component, inject, signal, computed} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import {MatOption, MatSelect} from '@angular/material/select';
import {OrderService} from '../_services/order.service';
import {MatIconModule} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AddOrderDialogComponent} from './add-order-dialog/add-order-dialog.component';
import {UpdateOrderDialogComponent} from './update-order-dialog/update-order-dialog.component';
import {CustomerService} from '../_services/customer.service';
import {Order} from '../_models/order';
import {OrderWithBatchKeys} from '../_models/order-with-batch-keys';

@Component({
  selector: 'app-batch',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatSelect,
    MatOption,
    MatIconModule
  ],
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent {
  private customerService = inject(CustomerService);
  private orderService = inject(OrderService);

  private iconRegistry = inject(MatIconRegistry);
  private sanitizer = inject(DomSanitizer);
  private dialog = inject(MatDialog);

  constructor() {
    this.iconRegistry.addSvgIcon('edit',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/edit.svg'));
    this.iconRegistry.addSvgIcon('delete',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/delete.svg'));
    this.iconRegistry.addSvgIcon('add',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/add.svg'));
  }

  searchOptions = ['Order Number', 'Customer Number', 'Customer Phone'];
  sortOptions = ["reg_date", "real_date", "price"];
  displayedColumns = [
    'number',
    'registrationDate',
    'expectedDate',
    'realDate',
    'deliveryCost',
    'totalCost',
    'customer',
    'customerNumber',
    'action'
  ];

  searchTerm = signal('');
  selectedSort = signal<string>(this.sortOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);

  dbUpdated = signal(0);
  orders = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    const sort = this.selectedSort();
    if (term) switch (this.selectedSearch()) {
      case 'Order Number':
        return this.orderService.getAllOrdersWithOrderNumber(term, sort)
      case 'Customer Number':
        return this.customerService.getCustomerOrdersByNumberSortedBy(term, sort);
      case 'Customer Phone':
        return this.customerService.getCustomerOrdersByPhoneSortedBy(term, sort);
    }
    return this.orderService.getAllOrdersSortedBy(sort);
  });

  updateDB() { this.dbUpdated.update(e => ++e); }

  delete(number: string) {
    const confirmation = this.dialog.open(ConfirmDialogComponent, {
      data: {message: `Are you sure you want to delete this Order?`}
    });
    confirmation.afterClosed().subscribe(res => {
      if (res) {
        this.orderService.deleteOrderByKey(number).subscribe(()=>{this.updateDB();});
      }
    });
  }

  update(number: string) {
    const update = this.dialog.open(UpdateOrderDialogComponent, {
      data: {order: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedOrder: Partial<Order> = {
          number: number,
          expectedDate: res.expectedDate,
          realDate: res.realDate,
          deliveryCost: res.deliveryCost
        }
        this.orderService.getOrderByKey(number).subscribe(curr => {
          if (curr.expectedDate == updatedOrder.expectedDate
            && curr.realDate == updatedOrder.realDate
            && curr.deliveryCost === updatedOrder.deliveryCost) return;
          this.orderService.updateOrder(updatedOrder).subscribe(() => {this.updateDB();});
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddOrderDialogComponent);
    input.afterClosed().subscribe(res => {
      if(res){
        let newOrder: Partial<OrderWithBatchKeys> = {
          registrationDate: res.registrationDate,
          expectedDate: res.expectedDate,
          deliveryCost: res.deliveryCost,
          customerNumber: res.customerNumber,
          batchKeys: res.batchKeys
        }
        this.orderService.addOrder(newOrder).subscribe(()=>{this.updateDB();});
      }
    });
  }
}
