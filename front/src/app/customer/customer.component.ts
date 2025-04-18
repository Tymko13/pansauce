import {Component, inject, signal, computed} from '@angular/core';
import {CommonModule, TitleCasePipe} from '@angular/common';
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
import {AddCustomerDialogComponent} from './add-customer-dialog/add-customer-dialog.component';
import {UpdateCustomerDialogComponent} from './update-customer-dialog/update-customer-dialog.component';
import {CustomerService} from '../_services/customer.service';
import {OrderWithBatchKeys} from '../_models/order-with-batch-keys';
import {
  MatDatepickerToggle,
  MatDateRangeInput,
  MatDateRangePicker, MatEndDate, MatStartDate
} from '@angular/material/datepicker';
import {Customer} from '../_models/customer';
import {PhoneService} from '../_services/phone.service';
import {CustomerWithOrders} from '../_models/customer-with-orders';

@Component({
  selector: 'app-customer',
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
    MatIconModule,
    TitleCasePipe,
    MatDatepickerToggle,
    MatDateRangeInput,
    MatDateRangePicker,
    MatStartDate,
    MatEndDate
  ],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent {
  private customerService = inject(CustomerService);
  private orderService = inject(OrderService);
  private phoneService = inject(PhoneService);

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

  searchOptions = ['customer_number', 'phone_number', 'full_name', 'type_number', 'type_name', 'sauce_number', 'sauce_name', 'batch_number'];
  showOptions = ["all", "order_period"];
  displayedColumns = [
    'number',
    'surname',
    'name',
    'patronymic',
    'address',
    'contacts',
    'action'
  ];

  searchTerm = signal('');
  selectedShow = signal<string>(this.showOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);

  startDate = signal<Date | null>(null);
  endDate = signal<Date | null>(null);

  dbUpdated = signal(0);
  customers = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    const search = this.selectedSearch();
    if(this.selectedShow()==='order_period'
      && this.startDate() != null
      && this.endDate() != null) return this.customerService.getCustomersWithOrdersBetweenDates(this.startDate()!, this.endDate()!);
    if (term) switch (search) {
      case 'customer_number':
        return this.customerService.getCustomersByNumber(term);
      case 'phone_number':
        return this.customerService.getCustomersByPhone(term);
      case 'full_name':
        return this.customerService.getCustomersByPIB(...term.split(' '));
      case 'type_number':
      case 'type_name':
      case 'sauce_number':
      case 'sauce_name':
      case 'batch_number':
        return this.customerService.getCustomersWithOrderThatHasThisAttribute(search, term);
    }
    return this.customerService.getAllCustomers();
  });

  updateDB() { this.dbUpdated.update(e => ++e); }

  delete(number: string) {
    const confirmation = this.dialog.open(ConfirmDialogComponent, {
      data: {message: `Are you sure you want to delete this Customer?`}
    });
    confirmation.afterClosed().subscribe(res => {
      if (res) {
        this.customerService.deleteCustomer(number).subscribe(()=>{this.updateDB();});
      }
    });
  }

  update(number: string) {
    const update = this.dialog.open(UpdateCustomerDialogComponent, {
      data: {customer: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedCustomer: Partial<Customer> = {
          number: number,
          name: res.name,
          surname: res.surname,
          patronymic: res.patronymic,
          address: res.address,
          phones: res.phones
        }
        this.customerService.updateCustomer(updatedCustomer).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddCustomerDialogComponent);
    input.afterClosed().subscribe(res => {
      if(res){
        let newCustomer: Partial<CustomerWithOrders> = {
          name: res.name,
          surname: res.surname,
          patronymic: res.patronymic,
          address: res.address,
          phones: res.phones,
          orders: [res.order]
        }
        console.log(newCustomer);
        this.customerService.addCustomer(newCustomer).subscribe(()=>{this.updateDB();});
      }
    });
  }
}
