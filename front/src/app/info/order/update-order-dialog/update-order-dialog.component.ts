import {Component, computed, Inject, inject, signal, WritableSignal} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {CommonModule} from '@angular/common';
import {
  AbstractControl,
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {OrderService} from '../../../_services/order.service';
import {Order} from '../../../_models/order';

@Component({
  standalone: true,
  selector: 'app-update-order-dialog',
  templateUrl: './update-order-dialog.component.html',
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
export class UpdateOrderDialogComponent {
  private fb = inject(FormBuilder);
  private orderService = inject(OrderService);

  order: WritableSignal<Partial<Order>> = signal({});

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { order: string },
    public dialogRef: MatDialogRef<UpdateOrderDialogComponent>
  ) {
    this.orderService.getOrderByKey(this.data.order).subscribe(data => {this.order.set(data);});
  }

  // dateValidator(date: Date): ValidatorFn {
  //   return (control: AbstractControl): ValidationErrors | null => {
  //     const endDate: Date = control.value;
  //     if (endDate &&  date && date < endDate) {
  //       return { dateRangeInvalid: true };
  //     }
  //     return null;
  //   };
  // }

  form = computed(() => this.fb.group({
    expectedDate: [this.order().expectedDate, [Validators.required]],
    realDate: [this.order().realDate, []]
  }));

  submit() {
    if (this.form().valid) {
      this.dialogRef.close(this.form().value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
