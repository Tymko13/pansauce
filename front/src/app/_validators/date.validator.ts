import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import {Ingredient} from '../_models/ingredient';

export function DatesValidator(startKey: string, endKey: string): ValidatorFn {
  return (group: AbstractControl): ValidationErrors | null => {
    const start = group.get(startKey)?.value;
    const end = group.get(endKey)?.value;

    if (start && end && new Date(start) > new Date(end)) {
      return { dateRangeInvalid: true };
    }
    return null;
  };
}

export function isUniqueIngrValidator(ingredients: Ingredient[], ingredient: Partial<Ingredient> | null = null): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    let isUnique = true;
    for(let ingr of ingredients) {
      if(ingr.name == ingredient?.name) continue;
      if(ingr.name == control.value) isUnique = false;
    }
    return !isUnique ? { notUnique: true } : null;
  };
}
