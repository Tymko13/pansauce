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
import {BatchService} from '../../_services/batch.service';
import {SauceService} from '../../_services/sauce.service';
import {OrderService} from '../../_services/order.service';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmDialogComponent} from '../../confirm-dialog/confirm-dialog.component';
import {UpdateBatchDialogComponent} from './update-batch-dialog/update-batch-dialog.component';
import {Batch} from '../../_models/batch';
import {AddBatchDialogComponent} from './add-batch-dialog/add-batch-dialog.component';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

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
    MatIconModule
  ],
  templateUrl: './batch.component.html',
  styleUrls: ['./batch.component.css'],
})
export class BatchComponent {
  private batchService = inject(BatchService);
  private sauceService = inject(SauceService);
  private orderService = inject(OrderService);
  private dialog = inject(MatDialog);
  private sanitizer = inject(DomSanitizer);

  searchOptions = ['Batch Number', 'Sauce Name', 'Sauce Number','Order Number'];
  sortOptions = ["number", "prod_date", "size", "price", "status"];
  showOptions = ["All", "SOLD", "IN STOCK"];
  displayedColumns = [
    'number',
    'orderNumber',
    'status',
    'productionDate',
    'expirationDate',
    'quantity',
    'sauce',
    'sauceCost',
    'cost',
    'action'
  ];

  searchTerm = signal('');
  selectedSort = signal<string>(this.sortOptions[0]);
  selectedShow = signal<string>(this.showOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);

  dbUpdated = signal(0);
  batches = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    let sort = this.selectedSort();
    if (sort === 'size') sort = "sauce_quantity";
    const show = this.selectedShow();

    switch (show) {
      case 'SOLD':
      case 'IN STOCK':
        return this.batchService.getAllBatchesWithStatus(show);
      case 'All':
        if (term) switch (this.selectedSearch()) {
          case 'Batch Number':
            return this.batchService.getBatchesByNumber(term);
          case 'Order Number':
            return this.orderService.getBatchesOfOrderSortedBy(term, sort);
          case 'Sauce Number':
            return this.sauceService.getSauceBatches(term, sort);
          case 'Sauce Name':
            return this.sauceService.getSauceBatchesByName(term, sort);
        }
    }
    return this.batchService.getAllBatchesSortedBy(sort);
  });

  updateDB() { this.dbUpdated.update(e => ++e); }

  delete(number: string) {
    const confirmation = this.dialog.open(ConfirmDialogComponent, {
      data: {message: `Are you sure you want to delete this Batch?`}
    });
    confirmation.afterClosed().subscribe(res => {
      if (res) {
        this.batchService.deleteBatch(number).subscribe(()=>{this.updateDB();});
      }
    });
  }

  update(number: string) {
    const update = this.dialog.open(UpdateBatchDialogComponent, {
      data: {batch: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedBatch: Partial<Batch> = {
          number: number,
          sauceCost: res.sauceCost,
          quantity: res.quantity
        }
        this.batchService.updateBatch(updatedBatch).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }

  add() {
    const input = this.dialog.open(AddBatchDialogComponent);
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

  pdfUrl: SafeResourceUrl | null = null;

  print() {
    const doc = new jsPDF({
      orientation: "landscape",
      format: "a4"
    });

    this.batches().subscribe(batches => {
      const rows = batches.map(batch => [
        batch.number,
        batch.orderNumber ?? '-',
        batch.status,
        new Date(batch.productionDate).toLocaleDateString(),
        new Date(batch.expirationDate).toLocaleDateString(),
        batch.quantity.toString(),
        batch.sauceName + '\n' + batch.sauceNumber,
        '$' + batch.sauceCost.toFixed(2),
        '$' + batch.cost.toFixed(2)
      ]);

      const headers = [
        'Batch #',
        'Order #',
        'Status',
        'Prod Date',
        'Exp Date',
        'Size',
        'Sauce',
        'Sauce Cost',
        'Total Cost'
      ];

      doc.text(new Date().toLocaleDateString(), doc.internal.pageSize.width - 40, 15);
      doc.setFontSize(24);
      doc.text("PAN SAUCE", 10, 15);
      doc.text("Batches report", 10 ,25);

      autoTable(doc, {
        head: [headers],
        body: rows,
        styles: { valign: "middle"},
        theme: "striped",
        startY: 35,
        didDrawPage: function (data)  {
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
      setTimeout(()=>this.printIframe(), 10);
    });
  }

  printIframe() {
    const iframe = document.querySelector('iframe');
    iframe?.contentWindow?.focus();
    iframe?.contentWindow?.print();
  }
}
