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

  getIncomeFromSoldBatchesBetweenDates(from: Date, to: Date): Observable<TotalIncome> {
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, {
      params: new HttpParams().set('from', from.toISOString()).set('to', to.toISOString())
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
