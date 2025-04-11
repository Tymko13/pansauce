import {Component, inject} from '@angular/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {SauceService} from '../../_services/sauce.service';
import {Sauce} from '../../_models/sauce';
import {Order} from '../../_models/order';
import {OrderService} from '../../_services/order.service';
import {DateValidator} from '../../_validators/date.validator';

@Component({
  standalone: true,
  selector: 'app-add-batch-dialog',
  templateUrl: './add-order-dialog.component.html',
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogContent,
    MatDialogActions,
    MatDialogTitle,
    MatSelectModule
  ],
  styles: "mat-form-field {margin-right: 1rem;}"
})
export class AddOrderDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddOrderDialogComponent>);
  private sauceService = inject(SauceService);
  private orderService = inject(OrderService);

  sauces: Sauce[] = [];
  orders: Order[] = [];
  constructor() {
    this.sauceService.findAllSauce("name").subscribe(data => {this.sauces = data;});
    this.orderService.getAllOrders().subscribe(data => {this.orders = data;});
  }

  form = this.fb.group({
    productionDate: [null, [Validators.required]],
    expirationDate: [null, Validators.required],
    quantity: [null, [Validators.required, Validators.min(1)]],
    sauceNumber: [null, Validators.required],
    orderNumber: [null]
  }, {validators: DateValidator('productionDate', 'expirationDate')});

  submit() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
