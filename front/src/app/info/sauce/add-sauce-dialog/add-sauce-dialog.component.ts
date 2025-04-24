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
import {TypeService} from '../../../_services/type.service';
import {Type} from '../../../_models/type';

@Component({
  standalone: true,
  selector: 'app-add-batch-dialog',
  templateUrl: './add-sauce-dialog.component.html',
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
  styles: "mat-form-field {width: 45%;} .left {margin-right: 5%;} #type {width: 25%; margin: 0 15%;}"
})
export class AddSauceDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddSauceDialogComponent>);
  private typeService = inject(TypeService);

  types: Type[] = [];
  isNewType = false;
  constructor() {
    this.typeService.findAllTypes().subscribe(data => {this.types = data;});
  }

  form = this.fb.group({
    name: [null, [Validators.required]],
    typeNumber: [null, [Validators.required]],
    typeName: [null],
    weight: [null, Validators.required],
    cost: [null, [Validators.required, Validators.min(1)]],
    shelfLife: [null, Validators.required],
    recipe: [null]
  });

  toggleType() {
    this.isNewType = !this.isNewType;
    if(this.isNewType){
      this.form.get("typeName")?.addValidators([Validators.required]);
      this.form.get("typeNumber")?.clearValidators();
      this.form.get("typeName")?.updateValueAndValidity();
    } else {
      this.form.get("typeNumber")?.addValidators([Validators.required]);
      this.form.get("typeName")?.clearValidators();
      this.form.get("typeNumber")?.updateValueAndValidity();
    }

  }

  submit() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }

  protected readonly name = name;
}
