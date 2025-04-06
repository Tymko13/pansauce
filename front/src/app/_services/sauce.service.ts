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
