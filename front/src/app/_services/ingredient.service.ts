import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { Ingredient } from '../_models/ingredient';

@Injectable({ providedIn: 'root' })
export class IngredientService {
  private apiUrl = `${environment.apiUrl}/ingredient`;

  constructor(private http: HttpClient) {}

  getAllIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.apiUrl);
  }

  getIngredientByKey(key: string): Observable<Ingredient> {
    return this.http.get<Ingredient>(`${this.apiUrl}/${key}`);
  }

  addIngredient(ingredient: Ingredient): Observable<void> {
    return this.http.post<void>(this.apiUrl, ingredient);
  }

  deleteIngredient(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
