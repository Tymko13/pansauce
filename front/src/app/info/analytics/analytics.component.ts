import { Component, computed, effect, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SauceWithIncome } from '../../_models/sauce-with-income';
import { SauceWithSalesCount } from '../../_models/sauce-with-sales-count';
import { SauceWithRecipe } from '../../_models/sauce-with-recipe';
import { TotalAmount } from '../../_models/total-amount';
import { TotalIncome } from '../../_models/total-income';
import { BatchService } from '../../_services/batch.service';
import { SauceService } from '../../_services/sauce.service';
import {CustomerService} from '../../_services/customer.service';
import {CustomerOrderData} from '../../_models/customer-order-data';

@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent {
  popularityType = 'top';

  saucesByIncome = signal<SauceWithIncome[]>([]);
  saucesBySales = signal<SauceWithSalesCount[]>([]);
  saucesRecipes = signal<SauceWithRecipe[]>([]);
  customerOrders = signal<CustomerOrderData[]>([]);

  isLoading = signal(true);
  error = signal<string | null>(null);


  selectedType = '';
  selectedSauceKey = '';
  fromDate = '';
  toDate = '';

  soldAmount = signal<TotalAmount | null>(null);
  totalIncome = signal<TotalIncome | null>(null);
  queryAmount = signal<TotalAmount | null>(null);
  queryIncome = signal<TotalIncome | null>(null);

  constructor(
    private batchService: BatchService,
    private sauceService: SauceService,
    private customerService: CustomerService
   // private customerOrderData: CustomerOrderData
  ) {
   this.loadCustomerData();
  }

  private getDateRange(): { from: Date; to: Date } | null {
    if (this.fromDate && this.toDate) {
      return { from: new Date(this.fromDate), to: new Date(this.toDate) };
    }
    return null;
  }

  loadPopularSauces(): void {
    this.sauceService.getTopSaucesWithIncome(this.popularityType).subscribe({
      next: data => this.saucesByIncome.set(data),
      error: err => console.error('Failed to load popular sauces by income', err)
    });
  }

  loadPopularSaucesBySales(): void {
    this.sauceService.getTopSaucesWithSalesCount(this.popularityType).subscribe({
      next: data => this.saucesBySales.set(data),
      error: err => console.error('Failed to load popular sauces by sales', err)
    });
  }

  loadPopularRecipes(byIncome: boolean = true): void {
    const loader = byIncome
      ? this.sauceService.getTopSaucesRecipeWithIncome(this.popularityType)
      : this.sauceService.getTopSaucesRecipeWithSalesCount(this.popularityType);

    loader.subscribe({
      next: data => this.saucesRecipes.set(data),
      error: err => console.error('Failed to load popular recipes', err)
    });
  }

  loadSoldAmountByType(): void {
    const range = this.getDateRange();
    if (range && this.selectedType) {
      this.batchService.getAmountByTypeKey(range.from, range.to, this.selectedType).subscribe({
        next: data => this.soldAmount.set(data),
        error: err => console.error('Failed to load sold amount by type', err)
      });
    }
  }

  loadTotalIncome(): void {
    const range = this.getDateRange();
    if (range) {
      this.batchService.getIncomeBetweenDates(range.from, range.to).subscribe({
        next: data => this.totalIncome.set(data),
        error: err => console.error('Failed to load total income', err)
      });
    }
  }

  loadAmountByKey(byType: boolean = true): void {
    const range = this.getDateRange();
    const key = byType ? this.selectedType : this.selectedSauceKey;

    if (range && key) {
      const loader = byType
        ? this.batchService.getAmountByTypeKey(range.from, range.to, key)
        : this.batchService.getAmountBySauceKey(range.from, range.to, key);

      loader.subscribe({
        next: data => this.queryAmount.set(data),
        error: err => console.error('Failed to load amount', err)
      });
    }
  }

  loadIncomeByKey(byType: boolean = true): void {
    const range = this.getDateRange();
    const key = byType ? this.selectedType : this.selectedSauceKey;

    if (range && key) {
      const loader = byType
        ? this.batchService.getIncomeByTypeKey(range.from, range.to, key)
        : this.batchService.getIncomeBySauceKey(range.from, range.to, key);

      loader.subscribe({
        next: data => this.queryIncome.set(data),
        error: err => console.error('Failed to load income', err)
      });
    }
  }

  private loadCustomerData() {
    this.customerService.getCustomersOrderData().subscribe({
      next: (data) => {
        this.customerOrders.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.error.set('Error while loading data');
        this.isLoading.set(false);
        console.error(err);
      }
    });
  }

  totalCustomers = computed(() => this.customerOrders().length);

  clearPopularityResults() {
    this.saucesByIncome = signal<SauceWithIncome[]>([]);
    this.saucesBySales = signal<SauceWithSalesCount[]>([]);
  }

  clearRecipesResults() {
    this.saucesRecipes = signal<SauceWithRecipe[]>([]);
  }

  clearSalesStatsResults() {
    this.soldAmount = signal<TotalAmount | null>(null);
  }

  clearIncomeResults() {
  }

  clearAmountIncomeResults() {
  }

}

