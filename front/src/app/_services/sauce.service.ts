import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sauce } from '../_models/sauce';
import { Batch } from '../_models/batch';
import { SauceWithRecipe } from '../_models/sauce-with-recipe';
import { SauceWithIncome } from '../_models/sauce-with-income';
import { SauceWithSalesCount } from '../_models/sauce-with-sales-count';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root'
})
export class SauceService {
  private apiUrl = `${environment.apiUrl}/sauce`;

  constructor(private http: HttpClient) {}

  findAllSauce(sorted: string): Observable<Sauce[]> {
    const params = new HttpParams().set('sorted', sorted);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getSauceBatches(number: string, sorted: string): Observable<Batch[]> {
    const params = new HttpParams().set('number', number).set('sorted', sorted);
    return this.http.get<Batch[]>(`${this.apiUrl}/batch`, { params });
  }

  getSauceBatchesByName(name: string, sorted: string): Observable<Batch[]> {
    const params = new HttpParams().set('name', name).set('sorted', sorted);
    return this.http.get<Batch[]>(`${this.apiUrl}/batch`, { params });
  }

  getAllSauceWithRecipe(sorted: string): Observable<SauceWithRecipe[]> {
    const params = new HttpParams().set('sorted', sorted);
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe`, { params });
  }

  getByNumberPrefixSorted(number: string, sorted: string): Observable<Sauce[]> {
    const params = new HttpParams().set('number', number).set('sorted', sorted);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getByNamePrefixSorted(name: string, sorted: string): Observable<Sauce[]> {
    const params = new HttpParams().set('name', name).set('sorted', sorted);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getTopSaucesWithIncome(popularity: string): Observable<SauceWithIncome[]> {
    const params = new HttpParams().set('popularity', popularity);
    return this.http.get<SauceWithIncome[]>(`${this.apiUrl}/income`, { params });
  }

  getTopSaucesWithSalesCount(popularity: string): Observable<SauceWithSalesCount[]> {
    const params = new HttpParams().set('popularity', popularity);
    return this.http.get<SauceWithSalesCount[]>(`${this.apiUrl}/sales`, { params });
  }

  getTopSaucesRecipeWithIncome(popularity: string): Observable<SauceWithRecipe[]> {
    const params = new HttpParams().set('popularity', popularity);
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe/income`, { params });
  }

  getTopSaucesRecipeWithSalesCount(popularity: string): Observable<SauceWithRecipe[]> {
    const params = new HttpParams().set('popularity', popularity);
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe/sales`, { params });
  }

  getSauceByKey(key: string): Observable<Sauce> {
    return this.http.get<Sauce>(`${this.apiUrl}/${key}`);
  }

  getSauceByNumber(number: string): Observable<Sauce[]> {
    const params = new HttpParams().set('number', number);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getSauceByName(name: string): Observable<Sauce[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  addSauce(sauce: Partial<Sauce>): Observable<void> {
    return this.http.post<void>(this.apiUrl, sauce);
  }

  deleteSauce(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}

