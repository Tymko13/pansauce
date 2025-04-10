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
import {Batch} from '../../_models/batch';
import {BatchService} from '../../_services/batch.service';

@Component({
  standalone: true,
  selector: 'app-add-batch-dialog',
  templateUrl: './update-batch-dialog.component.html',
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
export class UpdateBatchDialogComponent {
  private fb = inject(FormBuilder);
  private batchService = inject(BatchService);
  private orderService = inject(OrderService);

  orders: Order[] = [];
  batch: WritableSignal<Partial<Batch>> = signal({});

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { batch: string },
    public dialogRef: MatDialogRef<UpdateBatchDialogComponent>
  ) {
    this.batchService.getBatchByKey(this.data.batch).subscribe(data => {this.batch.set(data);});
    this.orderService.getAllOrders().subscribe(data => {this.orders = data;});
  }

  form = computed(() => this.fb.group({
    sauceCost: [this.batch().sauceCost, Validators.required],
    quantity: [this.batch().quantity, [Validators.required, Validators.min(1)]],
    orderNumber: [this.batch().orderNumber]
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
