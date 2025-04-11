import {Component, computed, Inject, inject, signal, WritableSignal} from '@angular/core';
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
import {Order} from '../../_models/order';
import {OrderService} from '../../_services/order.service';

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
  styles: "mat-form-field {margin-right: 1rem;}"
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

  form = computed(() => this.fb.group({
    expectedDate: [this.order().expectedDate, Validators.required],
    realDate: [this.order().realDate],
    deliveryCost: [this.order().deliveryCost]
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
