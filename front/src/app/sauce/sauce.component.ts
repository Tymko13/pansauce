import {Component, inject, signal, computed} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatOption, MatSelect} from '@angular/material/select';
import {SauceService} from '../_services/sauce.service';
import {MatIconModule} from '@angular/material/icon';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AddSauceDialogComponent} from './add-sauce-dialog/add-sauce-dialog.component';
import {UpdateSauceDialogComponent} from './update-sauce-dialog/update-sauce-dialog.component';
import {TypeService} from '../_services/type.service';
import {SauceIngredientService} from '../_services/sauce-ingredient.service';
import {SeeRecipeDialogComponent} from './see-recipe-dialog/see-recipe-dialog.component';
import {SauceWithRecipe} from '../_models/sauce-with-recipe';

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
  private sauceIngredientService = inject(SauceIngredientService);
  private typeService = inject(TypeService);

  private dialog = inject(MatDialog);

  searchOptions = ['Sauce Number', 'Sauce Name', 'Type Number', 'Type Name'];
  sortOptions = ["number", "name", "type", "price"];
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
  selectedSearch = signal<string>(this.searchOptions[0]);

  dbUpdated = signal(0);
  sauces = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    const sort = this.selectedSort();
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
    return this.sauceService.findAllSauce(sort);
  });

  updateDB() {
    this.dbUpdated.update(e => ++e);
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
      data: {sauce: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedSauce: Partial<SauceWithRecipe> = {
          number: number,
          cost: res.cost,
          shelfLife: res.shelfLife,
          name: res.name,
          recipe: res.recipe,
          weight: res.weight,
          typeNumber: res.typeNumber
        }
        this.sauceService.updateSauce(updatedSauce).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddSauceDialogComponent);
    input.afterClosed().subscribe(res => {
      if (res) {
        let newSauce: Partial<SauceWithRecipe> = {
          cost: res.cost,
          shelfLife: res.shelfLife,
          name: res.name,
          recipe: res.recipe,
          weight: res.weight,
          typeNumber: res.typeNumber
        }
        this.sauceService.addSauce(newSauce).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }
}
