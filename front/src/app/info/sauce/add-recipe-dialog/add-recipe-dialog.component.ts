import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {Ingredient} from '../../../_models/ingredient';
import {IngredientService} from '../../../_services/ingredient.service';
import {MatIcon} from '@angular/material/icon';
import {isUniqueIngrValidator} from '../../../_validators/date.validator';

@Component({
  standalone: true,
  selector: 'app-add-recipe-dialog',
  templateUrl: './add-recipe-dialog.component.html',
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
    MatSelectModule,
    MatIcon
  ],
  styles: ".no-scrollbar { scrollbar-width: none; max-height: 30vh; }"
})
export class AddRecipeDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddRecipeDialogComponent>);
  private ingredientService = inject(IngredientService);

  ingredients: Ingredient[] = [];

  constructor() {
    this.ingredientService.getAllIngredients().subscribe(data => {this.ingredients = data;});
    this.addIngredient();
  }

  form = this.fb.group({
    ingredients: this.fb.array([])
  });

  ingredientsArray(): FormArray {
    return this.form.get('ingredients') as FormArray;
  }

  newIngredient(): FormGroup {
    return this.fb.group({
      gti: [null, Validators.required],
      name: [null],
      weight: [null, [Validators.required, Validators.min(1)]]
    });
  }

  addIngredient() {
    this.ingredientsArray().push(this.newIngredient());
  }

  removeIngredient(index: number) {
    this.ingredientsArray().removeAt(index);
  }

  submit() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }

  alreadySelected(gti: string) {
    return (this.ingredientsArray().value as Array<any>).map(ingr => ingr.gti).includes(gti);
  }

  createNewIngredient(index: number) {
    this.ingredientsArray().at(index).get('gti')?.clearValidators();
    this.ingredientsArray().at(index).get('gti')?.setValue(null);
    this.ingredientsArray().at(index).get('name')?.addValidators([Validators.required, isUniqueIngrValidator(this.ingredients)]);
    this.ingredientsArray().at(index).get('gti')?.updateValueAndValidity();
    this.ingredientsArray().at(index).get('name')?.updateValueAndValidity();
    setTimeout(()=>document.getElementById(`newIngredient${index}`)?.focus());
  }

  isNewIngr(index: number) {
    return !this.ingredientsArray().at(index).get('gti')?.hasValidator(Validators.required);
  }

  getId(index: number) {
    return `newIngredient${index}`;
  }
}
