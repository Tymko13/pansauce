import {Component, Inject, inject, signal, WritableSignal} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {DateValidator} from '../../_validators/date.validator';
import {CustomerService} from '../../_services/customer.service';
import {Customer} from '../../_models/customer';
import {Batch} from '../../_models/batch';
import {BatchService} from '../../_services/batch.service';

@Component({
  standalone: true,
  selector: 'app-add-order-dialog',
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
  styles: "mat-form-field {width: 45%;} mat-form-field:nth-of-type(2n+1) {margin-right: 5%;}"
})
export class AddOrderDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddOrderDialogComponent>);
  private customerService = inject(CustomerService);
  private batchService = inject(BatchService);

  customers: Customer[] = [];
  batches: Batch[] = [];
  constructor(@Inject(MAT_DIALOG_DATA) public data: { askForCustomer: boolean }) {
    this.batchService.getAllBatchesSortedBy().subscribe(data => {
      this.batches = data.filter(batch => batch.orderNumber === null);
    });
    if(this.data.askForCustomer) {
      this.customerService.getAllCustomers().subscribe(data => {this.customers = data;});
    } else {
      this.form.controls.customerNumber.clearValidators();
    }
  }

  form = this.fb.group({
    registrationDate: [null, [Validators.required]],
    expectedDate: [null, Validators.required],
    deliveryCost: [null],
    customerNumber: [null, Validators.required],
    batchKeys: [null, Validators.required]
  }, {validators: DateValidator('registrationDate', 'expectedDate')});

  submit() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
