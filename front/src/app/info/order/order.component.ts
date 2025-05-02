import {Component, inject, signal, computed} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {CustomerService} from '../../_services/customer.service';
import {OrderService} from '../../_services/order.service';
import {MatDialog} from '@angular/material/dialog';
import {UpdateOrderDialogComponent} from './update-order-dialog/update-order-dialog.component';
import {Order} from '../../_models/order';
import {AddOrderDialogComponent} from './add-order-dialog/add-order-dialog.component';
import {OrderWithBatchKeys} from '../../_models/order-with-batch-keys';
import {AuthService} from '../../_auth/auth.service';


@Component({
  selector: 'app-order',
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
  private dialog = inject(MatDialog);
  authService = inject(AuthService);

  constructor() {
    if(this.authService.isSalesManager()) {
      this.displayedColumns = this.displayedColumns.slice(0, this.displayedColumns.length - 1);
    }
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

  update(number: string) {
    const update = this.dialog.open(UpdateOrderDialogComponent, {
      data: {order: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedOrder: Partial<Order> = {
          number: number,
          expectedDate: res.expectedDate,
          realDate: res.realDate
        }
        this.orderService.updateOrder(updatedOrder).subscribe(() => {this.updateDB();});
      }
    });
  }

  add() {
    const input = this.dialog.open(AddOrderDialogComponent, {data: {askForCustomer: true}});
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
