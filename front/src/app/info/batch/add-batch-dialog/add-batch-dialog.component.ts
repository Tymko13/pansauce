import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {SauceService} from '../../../_services/sauce.service';
import {Sauce} from '../../../_models/sauce';
import {DateValidator} from '../../../_validators/date.validator';


@Component({
  standalone: true,
  selector: 'app-add-batch-dialog',
  templateUrl: './add-batch-dialog.component.html',
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
export class AddBatchDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddBatchDialogComponent>);
  private sauceService = inject(SauceService);

  sauces: Sauce[] = [];
  constructor() {
    this.sauceService.findAllSauce("name").subscribe(data => {this.sauces = data;});
  }

  form = this.fb.group({
    productionDate: [null, [Validators.required]],
    expirationDate: [null, Validators.required],
    quantity: [null, [Validators.required, Validators.min(1)]],
    sauceNumber: [null, Validators.required]
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
