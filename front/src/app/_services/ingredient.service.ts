import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { Ingredient } from '../_models/ingredient';

@Injectable({ providedIn: 'root' })
export class IngredientService {

  private apiUrl = `${environment.apiUrl}/ingredient`;

  constructor(private http: HttpClient) { }

  getAllIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.apiUrl);
  }

  getAllIngredientsSortedBy(attribute: string): Observable<Ingredient[]> {
    const params = new HttpParams().set('sorted', attribute);
    return this.http.get<Ingredient[]>(this.apiUrl, { params });
  }

  getIngredientsWithNumberStartingWithSortedBy(ingredientNumber: string, attribute: string): Observable<Ingredient[]> {
    const params = new HttpParams()
      .set('number', ingredientNumber)
      .set('sorted', attribute);
    return this.http.get<Ingredient[]>(`${this.apiUrl}/search`, { params });
  }

  getIngredientsWithNameStartingWithSortedBy(ingredientName: string, attribute: string): Observable<Ingredient[]> {
    const params = new HttpParams()
      .set('name', ingredientName)
      .set('sorted', attribute);
    return this.http.get<Ingredient[]>(`${this.apiUrl}/search`, { params });
  }

  getIngredientByKey(key: string): Observable<Ingredient> {
    return this.http.get<Ingredient>(`${this.apiUrl}/${key}`);
  }

  addIngredient(ingredient: Partial<Ingredient>): Observable<void> {
    return this.http.post<void>(this.apiUrl, ingredient);
  }

  deleteIngredient(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }

  updateIngredient(ingredient: Partial<Ingredient>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, ingredient)
  }
}
