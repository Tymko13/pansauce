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
import {SauceService} from '../../_services/sauce.service';
import {TypeService} from '../../_services/type.service';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmDialogComponent} from '../../confirm-dialog/confirm-dialog.component';
import {SeeRecipeDialogComponent} from './see-recipe-dialog/see-recipe-dialog.component';
import {UpdateSauceDialogComponent} from './update-sauce-dialog/update-sauce-dialog.component';
import {SauceWithRecipe} from '../../_models/sauce-with-recipe';
import {AddSauceDialogComponent} from './add-sauce-dialog/add-sauce-dialog.component';
import {BatchService} from '../../_services/batch.service';
import {Sauce} from '../../_models/sauce';
import {Type} from '../../_models/type';
import {IngredientService} from '../../_services/ingredient.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import {AuthService} from '../../_auth/auth.service';

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
  private typeService = inject(TypeService);
  private dialog = inject(MatDialog);
  private sanitizer = inject(DomSanitizer);
  authService = inject(AuthService);
  saucesInBatches = signal<string[]>([]);
  allRecipes = signal<SauceWithRecipe[]>([]);

  constructor() {
    if(this.authService.isTopManager()){
      this.batchService.getAllBatchesSortedBy().subscribe(data => {
        this.saucesInBatches.set(data.flatMap(it => it.sauceNumber));
      });
    } else {
      this.displayedColumns = this.displayedColumns.slice(0, this.displayedColumns.length - 1)
    }
    this.updateAllInfo();
  }

  updateAllInfo() {
    this.sauceService.getAllSauceWithRecipe("name").subscribe(data => {
      this.allRecipes.set(data);
    });
  }

  canDelete(number: string) {
    return !this.saucesInBatches().includes(number);
  }

  searchOptions = ['Sauce Name', 'Sauce Number', 'Type Name', 'Type Number'];
  sortOptions = ["name", "number", "type", "price"];
  showOptions = ["All", "SAME RECIPE AS"];
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
    this.dialog.open(SeeRecipeDialogComponent, {
      data: {recipe: this.allRecipes().filter(sauce => sauce.number === number)[0].recipe}
    })
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

  getRecipe(number: string): string {
    let res= "";
    for(const ingr of this.allRecipes().filter(sauce => sauce.number === number)[0].recipe) {
      res += `${ingr.name} - ${ingr.weight} g\n`
    }
    return res.substring(0, res.length - 1);
  }

  pdfUrl: SafeResourceUrl | null = null;
  print() {
    const doc = new jsPDF({
      orientation: "landscape",
      format: "a4"
    });

    this.sauces().subscribe(sauces => {
      const rows = sauces.map(sauce => [
        sauce.number,
        sauce.name,
        sauce.typeName + '\n' + sauce.typeNumber,
        sauce.weight.toString() + ' g',
        sauce.shelfLife.toString() + ' d',
        '$' + sauce.cost.toFixed(2),
        this.getRecipe(sauce.number)
      ]);

      const headers = [
        'Sauce #',
        'Name',
        'Type',
        'Weight',
        'Shelf Life',
        'Cost',
        'Recipe'
      ];

      doc.text(new Date().toLocaleDateString(), doc.internal.pageSize.width - 40, 15);
      doc.setFontSize(24);
      doc.text("PAN SAUCE", 10, 15);
      doc.text("Sauces report", 10, 25);

      autoTable(doc, {
        head: [headers],
        body: rows,
        styles: {valign: "top"},
        theme: "striped",
        rowPageBreak: "avoid",
        startY: 35,
        didDrawPage: function (data) {
          const pageNumber = doc.getCurrentPageInfo().pageNumber;
          doc.setFontSize(12);
          doc.text(
            `Page ${pageNumber}`,
            doc.internal.pageSize.width - 20,
            doc.internal.pageSize.height - 5
          );
        },
      });
      const blob = doc.output('blob');
      const url = URL.createObjectURL(blob);
      this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
      setTimeout(() => {
        const iframe = document.querySelector('iframe');
        iframe?.contentWindow?.focus();
        iframe?.contentWindow?.print();
      }, 10);
    });
  }
}
