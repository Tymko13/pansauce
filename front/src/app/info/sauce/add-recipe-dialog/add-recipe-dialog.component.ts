import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {Ingredient} from '../../../_models/ingredient';
import {IngredientService} from '../../../_services/ingredient.service';

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
    MatSelectModule
  ],
  styles: ""
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
      ingredient: ['', Validators.required],
      weight: [null, [Validators.required, Validators.min(0)]]
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
      console.log(this.recipeForm.value);
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
