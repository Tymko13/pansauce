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
import {MatSelectModule} from '@angular/material/select';
import {SauceService} from '../../../_services/sauce.service';
import {TypeService} from '../../../_services/type.service';
import {Sauce} from '../../../_models/sauce';
import {Type} from '../../../_models/type';

@Component({
  standalone: true,
  selector: 'app-update-sauce-dialog',
  templateUrl: './update-sauce-dialog.component.html',
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
export class UpdateSauceDialogComponent {
  private fb = inject(FormBuilder);
  private sauceService = inject(SauceService);
  private typeService = inject(TypeService);

  sauce: WritableSignal<Partial<Sauce>> = signal({});
  types: Type[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { sauce: string },
    public dialogRef: MatDialogRef<UpdateSauceDialogComponent>
  ) {
    this.sauceService.getSauceByKey(this.data.sauce).subscribe(data => {this.sauce.set(data);});
    this.typeService.getAllTypes().subscribe(data => {this.types = data;})
  }

  form = computed(() => this.fb.group({
    name: [this.sauce().name, Validators.required],
    shelfLife: [this.sauce().shelfLife, [Validators.required, Validators.min(1)]],
    weight: [this.sauce().weight, [Validators.required, Validators.min(1)]],
    cost: [this.sauce().cost, [Validators.required, Validators.min(0)]],
    typeNumber: [this.sauce().typeNumber, Validators.required],
    typeName: [null]
  }));

  isNewType = false;
  toggleType() {
    this.isNewType = !this.isNewType;
    if(this.isNewType){
      this.form().get("typeName")?.addValidators([Validators.required]);
      this.form().get("typeNumber")?.clearValidators();
      this.form().get("typeName")?.updateValueAndValidity();
      this.form().get("typeNumber")?.updateValueAndValidity();
    } else {
      this.form().get("typeNumber")?.addValidators([Validators.required]);
      this.form().get("typeName")?.clearValidators();
      this.form().get("typeNumber")?.updateValueAndValidity();
      this.form().get("typeName")?.updateValueAndValidity();
    }
  }

  submit() {
    if (this.form().valid) {
      this.dialogRef.close(this.form().value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
