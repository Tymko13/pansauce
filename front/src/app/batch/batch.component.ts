import { Component, inject, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { Batch } from '../_models/batch';
import { BatchService } from '../_services/batch.service';
import {MatOption, MatSelect} from '@angular/material/select';
import {SauceService} from '../_services/sauce.service';
import {OrderService} from '../_services/order.service';

@Component({
  selector: 'app-batch',
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
  ],
  templateUrl: './batch.component.html',
  styleUrls: ['./batch.component.css'],
})
export class BatchComponent {
  private batchService = inject(BatchService);
  private sauceService = inject(SauceService);
  private orderService = inject(OrderService);

  batches = signal<Batch[]>([]);
  searchTerm = signal('');
  selectedSort = signal<string>("number");
  selectedShow = signal<string>("All");
  selectedOption = signal<string>('Batch');

  searchOptions = ['Batch', 'Order', 'Sauce Number', 'Sauce Name'];
  sortOptions = ["number", "prod_date", "size", "price", "status"];
  showOptions = ["All", "SOLD", "IN STOCK"]
  displayedColumns: string[] = [
    'number',
    'productionDate',
    'expirationDate',
    'sauceCost',
    'cost',
    'status',
    'sauce',
    'orderNumber',
  ];

  filteredBatches = computed(() => {
    const term = this.searchTerm();
    let sort = this.selectedSort();
    if(sort === 'size') sort = "sauce_quantity";
    const show = this.selectedShow();
    switch(show) {
      case 'All': {
        if(!term) return this.batchService.getAllBatchesSortedBy(sort);
        switch (this.selectedOption()) {
          case 'Batch': return this.batchService.getBatchByNumber(term);
          case 'Order': return this.orderService.getBatchesOfOrderSortedBy(term, sort);
          case 'Sauce Number': return this.sauceService.getSauceBatchesByNumber(term, sort);
          case 'Sauce Name': return this.sauceService.getSauceBatchesByName(term, sort);
          default: return this.batchService.getAllBatchesSortedBy(sort);
        }
      }
      case 'SOLD': return this.batchService.getAllBatchesWithStatus(show);
      case 'IN STOCK': return this.batchService.getAllBatchesWithStatus(show);
      default: return this.batchService.getAllBatchesSortedBy(sort);
    }

  });

  constructor() {
    this.batchService.getAllBatchesSortedBy().subscribe(data => this.batches.set(data));
  }

  selectSort(sort: string) {
    this.selectedSort.set(sort);
  }

  selectShow(show: string) {
    this.selectedShow.set(show);
  }
}
