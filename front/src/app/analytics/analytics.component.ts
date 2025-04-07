import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { BatchService } from '../_services/batch.service';
import { SauceService } from '../_services/sauce.service';
import { TotalIncome } from '../_models/total-income';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  imports: [
    FormsModule,
    CommonModule
  ],
  styleUrls: ['./analytics.component.css']
})

/*
12.	 Визначити 5 найбільш\найменш популярних соусів за виручкою\кількістю продажів
партій цього соусу
13.	Отримати список рецептів 5 найбільш\найменш популярних соусів за виручкою\кількістю
продажів партій цього соусу
14.	 Визначити загальну кількість проданих партій соусу конкретного типу,
проданих за певний проміжок часу
15.	 Визначити загальну виручку проданих партій за певний проміжок часу;
16.	 Визначити кількість\виручку проданих партій конкретного соусу\соусів певного типу
за певний проміжок часу;
 */

/*
у мене є проєкт на ангулярі, повноцінний застосунок від беку до фронту зі зверненням до бд.
я маю файли analytics.component.ts, analytics.component.html, analytics.component.css,
batch.service.ts , який виглядає отак
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../_environments/environment';
import {Batch} from '../_models/batch';
import {TotalAmount} from '../_models/total-amount';
import {TotalIncome} from '../_models/total-income';

@Injectable({
  providedIn: 'root',
})
export class BatchService {
  private apiUrl = `${environment.apiUrl}/batch`;

  constructor(private http: HttpClient) {}

  getAllBatchesSortedBy(attribute: string = 'prod_date'): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.apiUrl, {
      params: new HttpParams().set('sorted', attribute)
    });
  }

  getAllBatchesWithStatus(status: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.apiUrl, {
      params: new HttpParams().set('status', status)
    });
  }

  getAmountOfSoldBatchesBetweenDates(from: Date, to: Date): Observable<TotalAmount> {
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, {
      params: new HttpParams().set('from', from.toISOString()).set('to', to.toISOString())
    });
  }

  getIncomeFromSoldBatchesBetweenDates(from: Date, to: Date, typeKey: string): Observable<TotalIncome> {
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, {
      //params: new HttpParams().set('from', from.toISOString()).set('to', to.toISOString())
      params: new HttpParams()
        .set('from', from.toISOString())
        .set('to', to.toISOString()).set('type', typeKey)
    });
  }

  getAmountOfSoldBatchesBetweenDatesBySauceKey(sauce: string, from: Date, to: Date): Observable<TotalAmount> {
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, {
      params: new HttpParams()
        .set('sauce', sauce)
        .set('from', from.toISOString())
        .set('to', to.toISOString())
    });
  }

  getIncomeFromSoldBatchesBetweenDatesBySauceKey(sauce: string, from: Date, to: Date): Observable<TotalIncome> {
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, {
      params: new HttpParams()
        .set('sauce', sauce)
        .set('from', from.toISOString())
        .set('to', to.toISOString())
    });
  }

  getAmountOfSoldBatchesBetweenDatesByTypeKey(type: string, from: Date, to: Date): Observable<TotalAmount> {
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, {
      params: new HttpParams()
        .set('type', type)
        .set('from', from.toISOString())
        .set('to', to.toISOString())
    });
  }

  getIncomeFromSoldBatchesBetweenDatesByTypeKey(type: string, from: Date, to: Date): Observable<TotalIncome> {
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, {
      params: new HttpParams()
        .set('type', type)
        .set('from', from.toISOString())
        .set('to', to.toISOString())
    });
  }

  getBatchByKey(key: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(`${this.apiUrl}/${key}`);
  }

  getBatchByNumber(number: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(`${this.apiUrl}`, {
      params: new HttpParams()
        .set('number', number)
    });
  }

  addBatch(batch: Batch): Observable<void> {
    return this.http.post<void>(this.apiUrl, batch);
  }

  deleteBatch(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
та sauce.service.ts, який виглядає отак
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Sauce } from '../_models/sauce';
import { Batch } from '../_models/batch';
import { SauceWithRecipe } from '../_models/sauce-with-recipe';
import { SauceWithIncome } from '../_models/sauce-with-income';
import { SauceWithSalesCount } from '../_models/sauce-with-sales-count';

@Injectable({
  providedIn: 'root',
})
export class SauceService {
  private baseUrl = `${environment.apiUrl}/sauce`;

  constructor(private http: HttpClient) {}

  getAllSauce(attribute: string): Observable<Sauce[]> {
    return this.http.get<Sauce[]>(this.baseUrl, {
      params: new HttpParams().set('sorted', attribute)
    });
  }

  getSauceBatchesByNumber(sauceNumber: string, attribute: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(`${this.baseUrl}/batch`, {
      params: new HttpParams()
        .set('number', sauceNumber)
        .set('sorted', attribute)
    });
  }

  getSauceBatchesByName(sauceName: string, attribute: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(`${this.baseUrl}/batch`, {
      params: new HttpParams()
        .set('name', sauceName)
        .set('sorted', attribute)
    });
  }

  getAllSauceWithRecipe(attribute: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.baseUrl}/recipe`, {
      params: new HttpParams().set('sorted', attribute)
    });
  }

  getFiveSaucesWithIncome(popularity: string): Observable<SauceWithIncome[]> {
    return this.http.get<SauceWithIncome[]>(`${this.baseUrl}/income`, {
      params: new HttpParams().set('popularity', popularity)
    });
  }

  getFiveSaucesWithSalesCount(popularity: string): Observable<SauceWithSalesCount[]> {
    return this.http.get<SauceWithSalesCount[]>(`${this.baseUrl}/sales`, {
      params: new HttpParams().set('popularity', popularity)
    });
  }

  getFiveSaucesRecipeWithIncome(popularity: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.baseUrl}/recipe/income`, {
      params: new HttpParams().set('popularity', popularity)
    });
  }

  getFiveSaucesRecipeWithSalesCount(popularity: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.baseUrl}/recipe/sales`, {
      params: new HttpParams().set('popularity', popularity)
    });
  }

  getSauceByKey(key: string): Observable<Sauce> {
    return this.http.get<Sauce>(`${this.baseUrl}/${key}`);
  }

  getSauceByNumber(number: string): Observable<Sauce[]> {
    return this.http.get<Sauce[]>(this.baseUrl, {
      params: new HttpParams().set('number', number)
    });
  }

  getSauceByName(name: string): Observable<Sauce[]> {
    return this.http.get<Sauce[]>(this.baseUrl, {
      params: new HttpParams().set('name', name)
    });
  }

  addSauce(sauce: Sauce): Observable<void> {
    return this.http.post<void>(this.baseUrl, sauce);
  }

  deleteSauce(key: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${key}`);
  }


}
мені необхідно реалізувати такі функціональні вимоги:
12.	 Визначити 5 найбільш\найменш популярних соусів за виручкою\кількістю продажів
партій цього соусу
13.	Отримати список рецептів 5 найбільш\найменш популярних соусів за виручкою\кількістю
продажів партій цього соусу
14.	 Визначити загальну кількість проданих партій соусу конкретного типу,
проданих за певний проміжок часу
15.	 Визначити загальну виручку проданих партій за певний проміжок часу;
16.	 Визначити кількість\виручку проданих партій конкретного соусу\соусів певного типу
за певний проміжок часу;

я прошу тебе написати код , щоб на одній html сторінці можна було виконувати усі ці запити,
також напиши css, щоб це виглядало привабливо, ну і звісно найголовніше, щоб код дійсно виконував
функціонал, тому напиши AnalyticsComponent в analytics.component.ts
 */

export class AnalyticsComponent {
  popularity: string = 'income';
  sauces: any[] = [];
  selectedSauce: string = '';
  fromDate: string = '';
  toDate: string = '';
  batchData: any;
  revenueFromDate: string = '';
  revenueToDate: string = '';
  revenue: any;

  constructor(
    private batchService: BatchService,
    private sauceService: SauceService
  ) {}

  // Отримати 5 найбільш/найменш популярних соусів
  getFiveSaucesByPopularity(): void {
    if (this.popularity === 'income') {
      this.sauceService.getFiveSaucesWithIncome(this.popularity).subscribe(
        data => {
          this.sauces = data;
        },
        error => {
          console.error('Помилка при отриманні соусів:', error);
        }
      );
    } else if (this.popularity === 'sales') {
      this.sauceService.getFiveSaucesWithSalesCount(this.popularity).subscribe(
        data => {
          this.sauces = data;
        },
        error => {
          console.error('Помилка при отриманні соусів:', error);
        }
      );
    }
  }

  // Отримати кількість проданих партій для соусу за період
  getAmountOfSoldBatches(): void {
    if (this.selectedSauce && this.fromDate && this.toDate) {
      this.batchService.getAmountOfSoldBatchesBetweenDatesBySauceKey(this.selectedSauce, new Date(this.fromDate), new Date(this.toDate))
        .subscribe(data => {
          this.batchData = data;
        }, error => {
          console.error('Помилка при отриманні даних про партії:', error);
        });
    }
  }

  // Отримати виручку
  getRevenue(): void {
    if (this.revenueFromDate && this.revenueToDate) {
      this.batchService.getIncomeFromSoldBatchesBetweenDates(new Date(this.revenueFromDate), new Date(this.revenueToDate), 'someType')
        .subscribe(data => {
          this.revenue = data;
        }, error => {
          console.error('Помилка при отриманні виручки:', error);
        });
    }
  }
}


