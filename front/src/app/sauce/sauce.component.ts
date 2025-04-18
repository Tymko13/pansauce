import {Component, inject, signal, computed} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { BatchService } from '../_services/batch.service';
import {MatOption, MatSelect} from '@angular/material/select';
import {SauceService} from '../_services/sauce.service';
import {OrderService} from '../_services/order.service';
import {MatIconModule} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AddSauceDialogComponent} from './add-batch-dialog/add-sauce-dialog.component';
import {Batch} from '../_models/batch';
import {UpdateSauceDialogComponent} from './update-batch-dialog/update-sauce-dialog.component';

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
  private batchService = inject(BatchService);
  private sauceService = inject(SauceService);
  private orderService = inject(OrderService);

  private iconRegistry = inject(MatIconRegistry);
  private sanitizer = inject(DomSanitizer);
  private dialog = inject(MatDialog);

  constructor() {
    this.iconRegistry.addSvgIcon('edit',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/edit.svg'));
    this.iconRegistry.addSvgIcon('delete',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/delete.svg'));
    this.iconRegistry.addSvgIcon('add',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/add.svg'));
  }

  searchOptions = ['Sauce Number', 'Sauce Name', 'Type Number', 'Type Name'];
  sortOptions = ["name","number", "type", "cost"];
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
    // if (term) switch (this.selectedSearch()) {
    //   case 'Batch Number':
    //     return this.batchService.getBatchesByNumber(term);
    //   case 'Order Number':
    //     return this.orderService.getBatchesOfOrderSortedBy(term, sort);
    //   case 'Sauce Number':
    //     return this.sauceService.getSauceBatches(term, sort);
    //   case 'Sauce Name':
    //     return this.sauceService.getSauceBatchesByName(term, sort);
    // }
    return this.sauceService.getAllSauceWithRecipe(sort);
  });

  updateDB() { this.dbUpdated.update(e => ++e); }

  delete(number: string) {
    const confirmation = this.dialog.open(ConfirmDialogComponent, {
      data: {message: `Are you sure you want to delete this Sauce?`}
    });
    confirmation.afterClosed().subscribe(res => {
      if (res) {
        this.sauceService.deleteSauce(number).subscribe(()=>{this.updateDB();});
      }
    });
  }

  update(number: string) {
    const update = this.dialog.open(UpdateSauceDialogComponent, {
      data: {batch: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedBatch: Partial<Batch> = {
          number: number,
          sauceCost: res.sauceCost,
          quantity: res.quantity,
          orderNumber: res.orderNumber
        }
        this.batchService.getBatchByKey(number).subscribe(curr => {
          if (curr.sauceCost == updatedBatch.sauceCost
            && curr.quantity == updatedBatch.quantity
            && curr.orderNumber === updatedBatch.orderNumber) return;
          this.batchService.updateBatch(updatedBatch).subscribe(() => {
            this.updateDB();
          });
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddSauceDialogComponent);
    input.afterClosed().subscribe(res => {
      if(res){
        let newBatch: Partial<Batch> = {
          expirationDate: res.expirationDate,
          productionDate: res.productionDate,
          quantity: res.quantity,
          sauceNumber: res.sauceNumber
        }
        this.batchService.addBatch(newBatch).subscribe(()=>{this.updateDB();});
      }
    });
  }
}
