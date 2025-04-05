import {Sauce} from './sauce';
import {SauceIngredient} from './sauce-ingredient';

export interface SauceWithRecipe extends Sauce {
  recipe: SauceIngredient[];
}
