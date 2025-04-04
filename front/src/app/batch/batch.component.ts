import {Component, OnInit} from '@angular/core';
import {BatchService} from '../_services/batch.service';
import {Batch} from '../_models/batch';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-batch',
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './batch.component.html',
  standalone: true,
  styleUrl: './batch.component.css'
})

export class BatchComponent implements OnInit {
  batches: Batch[] = [];

  constructor(private batchService: BatchService) {}

  async ngOnInit(): Promise<void> {
    this.loadBatches();
  }

  loadBatches(): void {
    this.batchService.getBatches().subscribe((data) => {
      this.batches = data;
    });
  }

  deleteBatch(key: string): void {
    this.batchService.deleteBatch(key).subscribe(() => {
      this.loadBatches();
    });
  }

  protected readonly JSON = JSON;
}
