import {Component, ElementRef, inject, ViewChild} from '@angular/core';
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
import {MatDialog, MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {TypeService} from '../../../_services/type.service';
import {Type} from '../../../_models/type';
import {AddRecipeDialogComponent} from '../add-recipe-dialog/add-recipe-dialog.component';
import {Sauce} from '../../../_models/sauce';
import {SauceService} from '../../../_services/sauce.service';

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
  private dialog = inject(MatDialog);
  private typeService = inject(TypeService);
  private sauceService = inject(SauceService);

  types: Type[] = [];
  sauces: Sauce[] = [];
  isNewType = false;
  constructor() {
    this.typeService.getAllTypes().subscribe(data => {this.types = data;});
    this.sauceService.findAllSauce("number").subscribe(data => {this.sauces = data;});
  }

  duplicateValidator(sauces: Sauce[]): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const name = group.get('name')?.value;
      const weight = group.get('weight')?.value;
      let hasDuplicates = false;
      for (let sauce of sauces) {
        if (sauce.name == name && sauce.weight == weight) hasDuplicates = true;
      }
      return hasDuplicates ? {duplicateNames: true} : null;
    }
  }

  form = this.fb.group({
    name: [null, [Validators.required]],
    typeNumber: [null, [Validators.required]],
    typeName: [null],
    weight: [null, Validators.required, Validators.min(1)],
    cost: [null, [Validators.required, Validators.min(0.01)]],
    shelfLife: [null, Validators.required, Validators.min(1)],
    recipe: [null]
  }, {validators: this.duplicateValidator(this.sauces)});

  toggleType() {
    this.isNewType = !this.isNewType;
    if(this.isNewType){
      this.form.get("typeName")?.addValidators([Validators.required]);
      this.form.get("typeNumber")?.clearValidators();
      this.form.get("typeNumber")?.setValue(null);
      this.form.get("typeName")?.updateValueAndValidity();
      setTimeout(() => document.getElementById("newType")?.focus());
    } else {
      this.form.get("typeNumber")?.addValidators([Validators.required]);
      this.form.get("typeName")?.clearValidators();
      this.form.get("typeName")?.setValue(null);
      this.form.get("typeNumber")?.updateValueAndValidity();
    }
  }

  submit() {
    if (this.form.valid) {
      const recipe = this.dialog.open(AddRecipeDialogComponent);
      recipe.afterClosed().subscribe(res => {
        if(res) {
          this.form.get("recipe")?.setValue(res);
          this.dialogRef.close(this.form.value);
        }
      });
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
