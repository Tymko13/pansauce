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
import {TypeService} from '../../_services/type.service';

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

  sauceTypes = signal<any[]>([]);

  topSauces = signal<any[]>([]);
  leastSauces = signal<any[]>([]);
  topSales = signal<any[]>([]);
  leastSales = signal<any[]>([]);

  recipesByIncome = signal<any[]>([]);
  recipesBySales = signal<any[]>([]);

  selectedType = '';
  selectedSauceKey = '';
  fromDate = '';
  toDate = '';

  soldAmount = signal<TotalAmount | null>(null);
  totalIncome = signal<TotalIncome | null>(null);
  queryAmount = signal<TotalAmount | null>(null);
  queryIncome = signal<TotalIncome | null>(null);

  queryAmountT = signal<TotalAmount | null>(null);
  queryIncomeT = signal<TotalIncome | null>(null);

  favouriteSauce = signal<SauceWithSalesCount | null>(null);
  customers = signal<any[]>([]);
  customerKey: string = '';

  message = signal<string | null>(null);

  sauces = signal<any[]>([]);
  selectedSauceKeyToLoad: string = '';
  selectedSauceName: string = '';



  constructor(
    private batchService: BatchService,
    private sauceService: SauceService,
    private customerService: CustomerService,
    private typeService: TypeService
  ) {
   this.loadCustomerData();
   this.loadSaucesByIncome();
   this.loadSaucesBySales();
   this.loadPopularRecipes();
   this.loadCustomerDataFav();
   this.loadSauceData();
  }

  ngOnInit(): void {
    this.sauceService.getAllSauceTypes().subscribe({
      next: (types) => this.sauceTypes.set(types),
      error: (err) => console.error('Error:', err)
    });
  }

  // loadAllSauces(): void {
  //   this.sauceService.getAllSauces().subscribe({
  //     next: sauces => this.sauces.set(sauces),
  //     error: err => console.error('Failed to load sauces:', err)
  //   });
  // }

  loadSauceData() {
    this.sauceService.findAllSauce("number").subscribe({
      next: sauces => this.sauces.set(sauces),
      error: err => console.error('Failed to load sauces:', err)
    });
  }

  loadFavouriteSauce(key: string): void {
    if (key) {
      this.customerService.getCustomerFavouriteSauce(key).subscribe({
        next: sauce => this.favouriteSauce.set(sauce),
        error: err => console.error('Failed to load favourite sauce:', err)
      });
    }
  }

  private getDateRange(): { from: Date; to: Date } | null {
    if (this.fromDate && this.toDate) {
      return { from: new Date(this.fromDate), to: new Date(this.toDate) };
    }
    return null;
  }

  loadSaucesByIncome(): void {
    this.sauceService.getTopSaucesWithIncome('top').subscribe({
      next: data => this.topSauces.set(data),
      error: err => console.error('Failed to load top sauces by income', err)
    });

    this.sauceService.getTopSaucesWithIncome('last').subscribe({
      next: data => this.leastSauces.set(data),
      error: err => console.error('Failed to load least popular sauces by income', err)
    });
  }
  topSaucesSorted = computed(() =>
    [...this.topSauces()].sort((a, b) => b.sauceIncome - a.sauceIncome)
  );

  leastSaucesSorted = computed(() =>
    [...this.leastSauces()].sort((a, b) => a.sauceIncome - b.sauceIncome)
  );

  loadSaucesBySales(): void {
    this.sauceService.getTopSaucesWithSalesCount('top').subscribe({
      next: data => this.topSales.set(data),
      error: err => console.error('Failed to load top sauces by sales', err)
    });

    this.sauceService.getTopSaucesWithSalesCount('last').subscribe({
      next: data => this.leastSales.set(data),
      error: err => console.error('Failed to load least popular sauces by sales', err)
    });
  }

  topSalesSorted = computed(() =>
    [...this.topSales()].sort((a, b) => b.salesCount - a.salesCount)
  );

  leastSalesSorted = computed(() =>
    [...this.leastSales()].sort((a, b) => a.salesCount - b.salesCount)
  );

  loadPopularRecipes(): void {
    this.sauceService.getTopSaucesRecipeWithIncome(this.popularityType).subscribe({
      next: data => this.recipesByIncome.set(data),
      error: err => console.error('Failed to load recipes by income', err)
    });

    this.sauceService.getTopSaucesRecipeWithSalesCount(this.popularityType).subscribe({
      next: data => this.recipesBySales.set(data),
      error: err => console.error('Failed to load recipes by sales', err)
    });
  }

  loadSoldAmountByType(): void {
    console.log("works")
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
        next: data => {
          if (byType) {
            this.queryAmountT.set(data);
          } else {
            this.queryAmount.set(data);
          }
        },
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
        next: data => {
          const isEmpty = data === null;
          if (byType) {
            this.queryIncomeT.set(data);
          } else {
            this.queryIncome.set(data);
          }

          this.message.set(isEmpty ? 'Нічого не продали' : null);
        },
        error: err => {
          console.error('Failed to load income', err);
          this.message.set('Помилка завантаження доходу');
        }
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

  loadCustomerDataFav() {
    this.customerService.getAllCustomers().subscribe({
      next: customers => this.customers.set(customers),
      error: err => console.error('Failed to load customers:', err)
    });
  }

  totalCustomers = computed(() => this.customerOrders().length);

  clearPopularityResults() {
    this.saucesByIncome = signal<SauceWithIncome[]>([]);
    //this.saucesBySales = signal<SauceWithSalesCount[]>([]);
  }

  clearPopularityResults1() {
   // this.saucesByIncome = signal<SauceWithIncome[]>([]);
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

