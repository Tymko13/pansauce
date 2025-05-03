import {Component, inject, signal, computed} from '@angular/core';
import {
  MatDatepickerToggle,
  MatDateRangeInput,
  MatDateRangePicker, MatEndDate, MatStartDate
} from '@angular/material/datepicker';
import {CommonModule, TitleCasePipe} from '@angular/common';
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
import {MatDialog} from '@angular/material/dialog';
import {UpdateCustomerDialogComponent} from './update-customer-dialog/update-customer-dialog.component';
import {Customer} from '../../_models/customer';
import {AddCustomerDialogComponent} from './add-customer-dialog/add-customer-dialog.component';
import {CustomerWithOrdersAndBatches} from '../../_models/customer-with-orders-and-batches';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import {CustomerWithOrders} from '../../_models/customer-with-orders';
import {AuthService} from '../../_auth/auth.service';


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
  private dialog = inject(MatDialog);
  private sanitizer = inject(DomSanitizer);
  authService = inject(AuthService);
  customersWithOrders: CustomerWithOrders[] = [];

  constructor() {
    if (this.authService.isTopManager()) {
      this.customerService.getCustomersAndTheirOrders().subscribe(data => {
        this.customersWithOrders = data;
      });
    } else {
      this.searchOptions = this.searchOptions.slice(0, this.searchOptions.length - 5);
    }
  }

  searchOptions = ['phone_number', 'full_name', 'customer_number', 'type_number', 'type_name', 'sauce_number', 'sauce_name', 'batch_number'];

  showOptions = ["all", "order_period", "all_types", "one_type"];

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

    switch (this.selectedShow()) {
      case 'order_period':
        if (this.startDate() != null && this.endDate() != null)
          return this.customerService.getCustomersWithOrdersBetweenDates(this.startDate()!, this.endDate()!);
        else break;
      case 'all_types':
        return this.customerService.getCustomersWhoOrderedAllTypes();
      case 'one_type':
        return this.customerService.getCustomersWhoOrderedOnlyOneType();
      case 'all':
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
    }
    return this.customerService.getAllCustomers();
  });

  updateDB() {
    this.dbUpdated.update(e => ++e);
    if(this.authService.isTopManager()) {
      this.customerService.getCustomersAndTheirOrders().subscribe(data => {
        this.customersWithOrders = data;
      });
    }
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
      if (res) {
        let newCustomer: Partial<CustomerWithOrdersAndBatches> = {
          name: res.name,
          surname: res.surname,
          patronymic: res.patronymic,
          address: res.address,
          phones: res.phones,
          orders: [res.order],
          batchKeys: res.order.batchKeys
        }
        this.customerService.addCustomer(newCustomer).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  pdfUrl: SafeResourceUrl | null = null;

  print() {
    const doc = new jsPDF({
      orientation: "landscape",
      format: "a4"
    });

    this.customers().subscribe(data => {
      const customers = data.map(customer => customer.number);
      const customersWithOrders = this.customersWithOrders
        .filter(customer => customers.includes(customer.number))
        .flatMap(customer => customer.orders
          .map(order => ({...customer, order, orders: undefined, phones: customer.phones})));

      let prev: string = "";
      let nextCustomer = true;
      let numOfOrders = 0;
      const rows = customersWithOrders.map(customer => {
        if(prev == customer.number) nextCustomer = false;
        else {
          prev = customer.number;
          nextCustomer = true;
          numOfOrders = 0;
          for(let c of customersWithOrders) {
            if(c.number == customer.number) ++numOfOrders;
          }
        }
        let orders = [
          customer.order.number,
          customer.order.registrationDate.toString(),
          customer.order.expectedDate.toString(),
          customer.order.realDate?.toString() || '-',
          '$' + customer.order.deliveryCost?.toFixed(2) || '-',
          '$' + customer.order.totalCost.toFixed(2)
        ];
        if(nextCustomer) {
          return [...[
            {content: customer.number, rowSpan: numOfOrders},
            {content: customer.surname, rowSpan: numOfOrders},
            {content: customer.name, rowSpan: numOfOrders},
            {content: customer.patronymic || '-', rowSpan: numOfOrders},
            {content: customer.address, rowSpan: numOfOrders},
            {
              content: customer.phones.reduce((acc, val) => {
                return (acc + '\n' + val);
              }), rowSpan: numOfOrders
            }
          ], ...orders];
        } else return orders;
      });

      const headers = [
          'Customer #',
          'Surname',
          'Name',
          'Patronymic',
          'Address',
          'Phones',
          'Order #',
          'Reg Date',
          'Exp Date',
          'Real Date',
          'Del Cost',
          'Total Cost'
      ];

      doc.text(new Date().toLocaleDateString(), doc.internal.pageSize.width - 40, 15);
      doc.setFontSize(24);
      doc.text("PAN SAUCE", 10, 15);
      doc.text("Customers report", 10, 25);

      autoTable(doc, {
        head: [headers],
        body: rows,
        styles: {valign: "top", fontSize: 8},
        theme: "striped",
        startY: 35,
        didDrawPage: function (data) {
          const pageNumber = doc.getCurrentPageInfo().pageNumber;
          doc.setFontSize(12);
          doc.text(
            `Page ${pageNumber}`,
            doc.internal.pageSize.width - 20,
            doc.internal.pageSize.height - 5
          );
        },
      });
      const blob = doc.output('blob');
      const url = URL.createObjectURL(blob);
      this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
      setTimeout(() => {
        const iframe = document.querySelector('iframe');
        iframe?.contentWindow?.focus();
        iframe?.contentWindow?.print();
      }, 100);
    });
  }
}
