import {Component, inject, signal, computed, WritableSignal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {SauceService} from '../../_services/sauce.service';
import {SauceIngredientService} from '../../_services/sauce-ingredient.service';
import {TypeService} from '../../_services/type.service';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmDialogComponent} from '../../confirm-dialog/confirm-dialog.component';
import {SeeRecipeDialogComponent} from './see-recipe-dialog/see-recipe-dialog.component';
import {UpdateSauceDialogComponent} from './update-sauce-dialog/update-sauce-dialog.component';
import {SauceWithRecipe} from '../../_models/sauce-with-recipe';
import {AddSauceDialogComponent} from './add-sauce-dialog/add-sauce-dialog.component';
import {BatchService} from '../../_services/batch.service';
import {Sauce} from '../../_models/sauce';
import {Ingredient} from '../../_models/ingredient';
import {Type} from '../../_models/type';
import {IngredientService} from '../../_services/ingredient.service';

@Component({
  selector: 'app-sauce',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatSelect,
    MatOption,
    MatIconModule
  ],
  templateUrl: './sauce.component.html',
  styleUrls: ['./sauce.component.css'],
})
export class SauceComponent {
  private sauceService = inject(SauceService);
  private batchService = inject(BatchService);
  private sauceIngredientService = inject(SauceIngredientService);
  private ingredientService = inject(IngredientService);
  private typeService = inject(TypeService);

  private dialog = inject(MatDialog);
  saucesInBatches = signal<string[]>([]);
  allSauces = signal<Sauce[]>([]);
  allTypes = signal<Type[]>([]);
  allIngredients = signal<Ingredient[]>([]);

  constructor() {
    this.batchService.getAllBatchesSortedBy().subscribe(data => {
      this.saucesInBatches.set(data.flatMap(it => it.sauceNumber));
    });
    this.updateAllInfo();
  }

  updateAllInfo() {
    this.sauceService.findAllSauce("name").subscribe(data => {
      this.allSauces.set(data);
    });
    this.typeService.getAllTypes().subscribe(data => {
      this.allTypes.set(data);
    });
    this.ingredientService.getAllIngredients().subscribe(data => {
      this.allIngredients.set(data);
    });
  }

  canDelete(number: string) {
    return !this.saucesInBatches().includes(number);
  }

  searchOptions = ['Sauce Name', 'Sauce Number', 'Type Name', 'Type Number'];
  sortOptions = ["name", "number", "type", "price"];
  showOptions = ["All", "SAME RECIPE AS", "WITHOUT"];
  displayedColumns = [
    'number',
    'name',
    'type',
    'weight',
    'cost',
    'recipe',
    'shelfLife',
    'action'
  ];

  searchTerm = signal('');
  selectedSort = signal<string>(this.sortOptions[0]);
  selectedShow = signal<string>(this.showOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);
  selectedSauce = signal<Sauce | null>(null);
  selectedType = signal<Type | null>(null);
  selectedIngredient = signal<Ingredient | null>(null);

  dbUpdated = signal(0);
  sauces = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    const sort = this.selectedSort();

    switch (this.selectedShow()) {
      case 'SAME RECIPE AS':
        if (this.selectedSauce() !== null)
          return this.sauceService.getSaucesWithAlikeRecipe(this.selectedSauce()!.number);
        else break;
      case 'WITHOUT':
        if (this.selectedType() !== null && this.selectedIngredient() !== null)
          return this.sauceService.getSaucesWithoutTypeAndIngredient(this.selectedType()!.typeNumber, this.selectedIngredient()!.gti);
        else break;
      case 'All':
        if (term) switch (this.selectedSearch()) {
          case 'Sauce Number':
            return this.sauceService.getByNumberPrefixSorted(term, sort);
          case 'Sauce Name':
            return this.sauceService.getByNamePrefixSorted(term, sort);
          case 'Type Number':
            return this.typeService.getSaucesWithTypeNumberSortedBy(term, sort);
          case 'Type Name':
            return this.typeService.getSaucesWithTypeNameSortedBy(term, sort);
        }
    }
    return this.sauceService.findAllSauce(sort);
  });

  updateDB() {
    this.dbUpdated.update(e => ++e);
    this.updateAllInfo();
  }

  delete(number: string) {
    const confirmation = this.dialog.open(ConfirmDialogComponent, {
      data: {message: `Are you sure you want to delete this Sauce?`}
    });
    confirmation.afterClosed().subscribe(res => {
      if (res) {
        this.sauceService.deleteSauce(number).subscribe(() => {
          this.updateDB();
        });
      }
    });

  }

  seeRecipe(number: string) {
    this.sauceIngredientService.getSauceIngredientsByKey(number).subscribe(recipe => {
      if (recipe) {
        this.dialog.open(SeeRecipeDialogComponent, {
          data: {recipe: recipe}
        })
      }
    });
  }

  update(number: string) {
    const update = this.dialog.open(UpdateSauceDialogComponent, {
      maxHeight: '50vh',
      data: {sauce: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedSauce: Partial<SauceWithRecipe> = {
          number: number,
          cost: res.cost,
          shelfLife: res.shelfLife,
          name: res.name,
          recipe: [...res.recipe.ingredients],
          weight: res.weight,
          typeNumber: res.typeNumber,
          typeName: res.typeName
        }
        this.sauceService.updateSauce(updatedSauce).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddSauceDialogComponent, {maxHeight: '50vh'});
    input.afterClosed().subscribe(res => {
      if (res) {
        let newSauce: Partial<SauceWithRecipe> = {
          cost: res.cost,
          shelfLife: res.shelfLife,
          name: res.name,
          recipe: [...res.recipe.ingredients],
          weight: res.weight,
          typeNumber: res.typeNumber,
          typeName: res.typeName
        }
        this.sauceService.addSauce(newSauce).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  protected readonly name = name;
}
