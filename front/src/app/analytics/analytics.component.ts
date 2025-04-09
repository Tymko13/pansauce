import {Component, signal} from '@angular/core';
import { BatchService } from '../_services/batch.service';
import { SauceService } from '../_services/sauce.service';
import { TotalAmount } from '../_models/total-amount';
import { TotalIncome } from '../_models/total-income';
import { SauceWithIncome } from '../_models/sauce-with-income';
import { SauceWithSalesCount } from '../_models/sauce-with-sales-count';
import { SauceWithRecipe } from '../_models/sauce-with-recipe';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  imports: [
    FormsModule,
    CommonModule
  ],
  standalone: true,
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent {
  popularityType: string = 'top';
  // saucesByIncome: SauceWithIncome[] = [];
  saucesByIncome = signal<SauceWithIncome[]>([]);
  saucesBySales: SauceWithSalesCount[] = [];
  saucesRecipes: SauceWithRecipe[] = [];
  selectedType: string = '';
  selectedSauceKey: string = '';
  fromDate: string = '';
  toDate: string = '';
  soldAmount: TotalAmount | null = null;
  totalIncome: TotalIncome | null = null;
  queryAmount: TotalAmount | null = null;
  queryIncome: TotalIncome | null = null;

  constructor(private batchService: BatchService, private sauceService: SauceService) {}

  loadPopularSauces(): void {
    this.sauceService.getFiveSaucesWithIncome(this.popularityType).subscribe(data => {
      console.log(data);
      this.saucesByIncome.set(data);
    });
  }

  loadPopularSaucesBySales(): void {
    this.sauceService.getFiveSaucesWithSalesCount(this.popularityType).subscribe(data => {
      this.saucesBySales = data;
    });
  }

  loadPopularRecipesByIncome(): void {
    this.sauceService.getFiveSaucesRecipeWithIncome(this.popularityType).subscribe(data => {
      this.saucesRecipes = data;
    });
  }

  loadPopularRecipesBySales(): void {
    this.sauceService.getFiveSaucesRecipeWithSalesCount(this.popularityType).subscribe(data => {
      this.saucesRecipes = data;
    });
  }

  loadSoldAmountByType(): void {
    if (this.selectedType && this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getAmountOfSoldBatchesBetweenDatesByTypeKey(this.selectedType, from, to).subscribe(data => {
        this.soldAmount = data;
      });
    }
  }

  loadTotalIncome(): void {
    if (this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getIncomeFromSoldBatchesBetweenDates(from, to).subscribe(data => {
        this.totalIncome = data;
      });
    }
  }

  loadAmountBySauceKey(): void {
    if (this.selectedSauceKey && this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getAmountOfSoldBatchesBetweenDatesBySauceKey(this.selectedSauceKey, from, to).subscribe(data => {
        this.queryAmount = data;
      });
    }
  }

  loadIncomeBySauceKey(): void {
    if (this.selectedSauceKey && this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getIncomeFromSoldBatchesBetweenDatesBySauceKey(this.selectedSauceKey, from, to).subscribe(data => {
        this.queryIncome = data;
      });
    }
  }

  loadAmountByTypeKey(): void {
    if (this.selectedType && this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getAmountOfSoldBatchesBetweenDatesByTypeKey(this.selectedType, from, to).subscribe(data => {
        this.queryAmount = data;
      });
    }
  }

  loadIncomeByTypeKey(): void {
    if (this.selectedType && this.fromDate && this.toDate) {
      const from = new Date(this.fromDate);
      const to = new Date(this.toDate);
      this.batchService.getIncomeFromSoldBatchesBetweenDatesByTypeKey(this.selectedType, from, to).subscribe(data => {
        this.queryIncome = data;
      });
    }
  }
}

