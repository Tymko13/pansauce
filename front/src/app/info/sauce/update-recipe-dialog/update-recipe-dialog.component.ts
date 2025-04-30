import {Component, Inject, inject, signal, WritableSignal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule, ValidationErrors, ValidatorFn,
  Validators
} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {Ingredient} from '../../../_models/ingredient';
import {IngredientService} from '../../../_services/ingredient.service';
import {MatIcon} from '@angular/material/icon';
import {SauceIngredient} from '../../../_models/sauce-ingredient';
import {SauceIngredientService} from '../../../_services/sauce-ingredient.service';

@Component({
  standalone: true,
  selector: 'app-update-recipe-dialog',
  templateUrl: './update-recipe-dialog.component.html',
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
export class UpdateRecipeDialogComponent {
  private fb = inject(FormBuilder);
  private ingredientService = inject(IngredientService);
  private sauceIngredientService = inject(SauceIngredientService);

  ingredients: Ingredient[] = [];
  original: SauceIngredient[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: { sauce: string },
              public dialogRef: MatDialogRef<UpdateRecipeDialogComponent>) {
    this.ingredientService.getAllIngredients().subscribe(data => {this.ingredients = data;});
    this.sauceIngredientService.getSauceIngredientsByKey(this.data.sauce).subscribe(data => {
      this.original = data;
      for(let ingr of this.original) {
        this.addIngredient(ingr.gti, ingr.weight);
      }
    });
  }

  form = this.fb.group({
    ingredients: this.fb.array([])
  });

  ingredientsArray(): FormArray {
    return this.form.get('ingredients') as FormArray;
  }

  newIngredient(gti: string | null = null, weight: number | null = null): FormGroup {
    return this.fb.group({
      gti: [gti, Validators.required],
      name: [null],
      weight: [weight, [Validators.required, Validators.min(0)]]
    });
  }

  addIngredient(gti: string | null = null, weight: number | null = null) {
    this.ingredientsArray().push(this.newIngredient(gti, weight));
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

  isUniqueValidator(ingredients: Ingredient[]): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let isUnique = true;
      for(let ingr of ingredients) {
        if(ingr.name == control.value) isUnique = false;
      }
      return !isUnique ? { notUnique: true } : null;
    };
  }

  createNewIngredient(index: number) {
    this.ingredientsArray().at(index).get('gti')?.clearValidators();
    this.ingredientsArray().at(index).get('gti')?.setValue(null);
    this.ingredientsArray().at(index).get('name')?.addValidators([Validators.required, this.isUniqueValidator(this.ingredients)]);
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
