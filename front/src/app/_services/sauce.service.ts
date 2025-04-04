import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Sauce } from '../_models/sauce';
import { Batch } from '../_models/batch';
import { SauceWithRecipe } from '../_models/sauce-with-recipe';
import { SauceWithIncome } from '../_models/sauce-with-income';
import { SauceWithSalesCount } from '../_models/sauce-with-sales-count';

@Injectable({ providedIn: 'root' })
export class SauceService {
  private apiUrl = `${environment.apiUrl}/sauce`;

  constructor(private http: HttpClient) {}

  findAllSauce(attribute: string): Observable<Sauce[]> {
    return this.http.get<Sauce[]>(`${this.apiUrl}?sorted=${attribute}`);
  }

  getSauceBatches(sauceKey: string, attribute: string): Observable<Batch[]> {
    return this.http.get<Batch[]>(`${this.apiUrl}/${sauceKey}/batch?sorted=${attribute}`);
  }

  getAllSauceWithRecipe(attribute: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe?sorted=${attribute}`);
  }

  getFiveSaucesWithIncome(popularity: string): Observable<SauceWithIncome[]> {
    return this.http.get<SauceWithIncome[]>(`${this.apiUrl}/income?popularity=${popularity}`);
  }

  getFiveSaucesWithSalesCount(popularity: string): Observable<SauceWithSalesCount[]> {
    return this.http.get<SauceWithSalesCount[]>(`${this.apiUrl}/sales?popularity=${popularity}`);
  }

  getFiveSaucesRecipeWithIncome(popularity: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe/income?popularity=${popularity}`);
  }

  getFiveSaucesRecipeWithSalesCount(popularity: string): Observable<SauceWithRecipe[]> {
    return this.http.get<SauceWithRecipe[]>(`${this.apiUrl}/recipe/sales?popularity=${popularity}`);
  }

  getSauceByKey(key: string): Observable<Sauce> {
    return this.http.get<Sauce>(`${this.apiUrl}/${key}`);
  }

  addSauce(sauce: Sauce): Observable<void> {
    return this.http.post<void>(this.apiUrl, sauce);
  }

  deleteSauce(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
