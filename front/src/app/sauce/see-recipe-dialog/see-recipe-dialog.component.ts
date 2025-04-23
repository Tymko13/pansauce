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
import {OrderService} from '../../_services/order.service';
import {Batch} from '../../_models/batch';
import {BatchService} from '../../_services/batch.service';
import {OrderWithCustomerData} from '../../_models/order-with-customer-data';
import {SauceWithRecipe} from '../../_models/sauce-with-recipe';

@Component({
  standalone: true,
  selector: 'app-see-recipe-dialog',
  templateUrl: './see-recipe-dialog.component.html',
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
export class SeeRecipeDialogComponent {
  // private fb = inject(FormBuilder);
  // private batchService = inject(BatchService);
  // private orderService = inject(OrderService);
  //
  // orders: OrderWithCustomerData[] = [];
  // batch: WritableSignal<Partial<Batch>> = signal({});
  //
  // constructor(
  //   @Inject(MAT_DIALOG_DATA) public data: { sauce: SauceWithRecipe },
  //   public dialogRef: MatDialogRef<SeeRecipeDialogComponent>
  // ) {
  //   this.batchService.getBatchByKey(this.data.batch).subscribe(data => {this.batch.set(data);});
  //   this.orderService.getAllOrders().subscribe(data => {this.orders = data;});
  // }
  //
  // form = computed(() => this.fb.group({
  //   sauceCost: [this.batch().sauceCost, Validators.required],
  //   quantity: [this.batch().quantity, [Validators.required, Validators.min(1)]],
  //   orderNumber: [{
  //     value: this.batch().orderNumber,
  //     disabled: this.batch().status !== "IN STOCK"
  //   }]
  // }));
  //
  // submit() {
  //   if (this.form().valid) {
  //     this.dialogRef.close(this.form().value);
  //   }
  // }
  //
  // cancel() {
  //   this.dialogRef.close(null);
  // }
}
