import {Component, computed, Inject, inject, signal, WritableSignal} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {BatchService} from '../../../_services/batch.service';
import {Batch} from '../../../_models/batch';

@Component({
  standalone: true,
  selector: 'app-update-batch-dialog',
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
    MatDialogTitle
  ],
  styles: "mat-form-field {width: 45%;} mat-form-field:nth-of-type(2n+1) {margin-right: 5%;}"
})
export class UpdateBatchDialogComponent {
  private fb = inject(FormBuilder);
  private batchService = inject(BatchService);

  batch: WritableSignal<Partial<Batch>> = signal({});

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { batch: string },
    public dialogRef: MatDialogRef<UpdateBatchDialogComponent>
  ) {
    this.batchService.getBatchByKey(this.data.batch).subscribe(data => {this.batch.set(data);});
  }

  form = computed(() => this.fb.group({
    sauceCost: [this.batch().sauceCost, Validators.required],
    quantity: [this.batch().quantity, [Validators.required, Validators.min(1)]],
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
