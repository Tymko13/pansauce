import {Component, computed, Inject, inject, signal} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {CommonModule} from '@angular/common';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {IngredientService} from '../../../_services/ingredient.service';
import {Ingredient} from '../../../_models/ingredient';
import {isUniqueIngrValidator} from '../../../_validators/date.validator';

@Component({
  standalone: true,
  selector: 'app-update-ingredient-dialog',
  templateUrl: './update-ingredient-dialog.component.html',
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
  styles: "mat-form-field {width: 100%;}"
})
export class UpdateIngredientDialogComponent {
  private fb = inject(FormBuilder);
  private ingredientService = inject(IngredientService);

  ingr = signal<Partial<Ingredient>>({});
  allIngrs = signal<Ingredient[]>([]);

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { ingredient: string },
    public dialogRef: MatDialogRef<UpdateIngredientDialogComponent>
  ) {
    this.ingredientService.getIngredientByKey(this.data.ingredient).subscribe(data => {this.ingr.set(data);});
    this.ingredientService.getAllIngredients().subscribe(data => {this.allIngrs.set(data);});
  }

  form = computed(() => this.fb.group({
    name: [this.ingr().name, [Validators.required, isUniqueIngrValidator(this.allIngrs(), this.ingr())]],
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
