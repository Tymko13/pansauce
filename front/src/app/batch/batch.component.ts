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

  batches = signal<Batch[]>([]);
  searchTerm = signal('');
  selectedSort = signal<string>("number");

  selectedOption = signal<string>('Batch');
  searchOptions = ['Batch', 'Order', 'Sauce Number', 'Sauce Name'];

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
    console.log(this.selectedOption());
    console.log(this.searchTerm());
    const term = this.searchTerm();
    const sort = this.selectedSort();
    if(!term) return this.batchService.getAllBatchesSortedBy(sort);
    switch (this.selectedOption()) {
      case 'Batch': return this.batchService.getBatchByNumber(term);
      case 'Order': return this.batchService.getBatchByKey(term);
      case 'Sauce Number': return this.sauceService.getSauceBatchesByNumber(term, sort);
      case 'Sauce Name': return this.sauceService.getSauceBatchesByName(term, sort);
      default: return this.batchService.getAllBatchesSortedBy(sort);
    }
  });

  constructor() {
    this.batchService.getAllBatchesSortedBy().subscribe(data => this.batches.set(data));
  }

  // clearSort() {
  //   this.selectedSort.set(null);
  // }

  selectSort(sort: string) {
    this.selectedSort.set(sort);
  }

  availableSort = ["number", "prod_date", "sauce_quantity", "price", "status"];
}
