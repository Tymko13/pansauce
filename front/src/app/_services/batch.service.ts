import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Batch } from '../_models/batch';
import { TotalAmount } from '../_models/total-amount';
import { TotalIncome } from '../_models/total-income';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root'
})
export class BatchService {
  private apiUrl = `${environment.apiUrl}/batch`;

  constructor(private http: HttpClient) {}

  getAllBatchesSortedBy(attribute: string = 'prod_date'): Observable<Batch[]> {
    const params = new HttpParams().set('sorted', attribute);
    return this.http.get<Batch[]>(this.apiUrl, { params });
  }

  getAllBatchesWithStatus(status: string): Observable<Batch[]> {
    const params = new HttpParams().set('status', status);
    return this.http.get<Batch[]>(this.apiUrl, { params });
  }

  getAmountBetweenDates(from: Date, to: Date): Observable<TotalAmount> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0]);
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, { params });
  }

  getIncomeBetweenDates(from: Date, to: Date): Observable<TotalIncome> {
    const params = new HttpParams().
    set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0]);
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, { params });
  }

  getAmountBySauceKey(from: Date, to: Date, sauce: string): Observable<TotalAmount> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0])
      .set('sauce', sauce);
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, { params });
  }

  getIncomeBySauceKey(from: Date, to: Date, sauce: string): Observable<TotalIncome> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0])
      .set('sauce', sauce);
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, { params });
  }

  getAmountByTypeKey(from: Date, to: Date, type: string): Observable<TotalAmount> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0])
      .set('type', type);
    return this.http.get<TotalAmount>(`${this.apiUrl}/amount`, { params });
  }

  getIncomeByTypeKey(from: Date, to: Date, type: string): Observable<TotalIncome> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0])
      .set('type', type);
    return this.http.get<TotalIncome>(`${this.apiUrl}/income`, { params });
  }

  getBatchByKey(key: string): Observable<Batch> {
    return this.http.get<Batch>(`${this.apiUrl}/${key}`);
  }

  getBatchesByNumber(number: string): Observable<Batch[]> {
    const params = new HttpParams().set('number', number);
    return this.http.get<Batch[]>(this.apiUrl, { params });
  }

  addBatch(batch: Partial<Batch>): Observable<void> {
    console.log(batch);
    return this.http.post<void>(this.apiUrl, batch);
  }

  deleteBatch(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }

  updateBatch(batch: Partial<Batch>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, batch);
  }
}

