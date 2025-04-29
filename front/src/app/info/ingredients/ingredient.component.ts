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
import {MatIconModule} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {UpdateIngredientDialogComponent} from './update-ingredient-dialog/update-ingredient-dialog.component';
import {Batch} from '../../_models/batch';
import {IngredientService} from '../../_services/ingredient.service';
import {Ingredient} from '../../_models/ingredient';


@Component({
  selector: 'app-ingredient',
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
  templateUrl: './ingredient.component.html',
  styleUrls: ['./ingredient.component.css'],
})
export class IngredientComponent {
  private ingredientService = inject(IngredientService);
  private dialog = inject(MatDialog);

  searchOptions = ['GTI Number', 'Ingredient Name'];
  sortOptions = ["number", "name"];
  showOptions = ["All"]
  displayedColumns = [
    'gti_number',
    'name',
    'action'
  ];

  searchTerm = signal('');
  selectedSort = signal<string>(this.sortOptions[0]);
  selectedShow = signal<string>(this.showOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);

  dbUpdated = signal(0);
  ingrs = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    let sort = this.selectedSort();
    if (sort === 'size') sort = "sauce_quantity";
    const show = this.selectedShow();

    switch (show) {
      case 'All': {
        if (term) switch (this.selectedSearch()) {
          case 'GTI Number':
            return this.ingredientService.getIngredientsWithNumberStartingWithSortedBy(term, sort);
          case 'Ingredient Name':
            return this.ingredientService.getIngredientsWithNameStartingWithSortedBy(term, sort);
        }
        break;
      }
    }
    return this.ingredientService.getAllIngredientsSortedBy(sort);
  });

  updateDB() {
    this.dbUpdated.update(e => ++e);
  }

  update(number: string) {
    const update = this.dialog.open(UpdateIngredientDialogComponent, {
      data: {ingredient: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedIngredient: Partial<Ingredient> = {
          gti: number,
          name: res.name
        }
        this.ingredientService.updateIngredient(updatedIngredient).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }
}
