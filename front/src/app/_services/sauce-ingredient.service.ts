import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { SauceIngredient } from '../_models/sauce-ingredient';

@Injectable({ providedIn: 'root' })
export class SauceIngredientService {
  private apiUrl = `${environment.apiUrl}/sauce`;

  constructor(private http: HttpClient) {}

  getSauceIngredientsByKey(key: string): Observable<SauceIngredient[]> {
    return this.http.get<SauceIngredient[]>(`${this.apiUrl}/${key}/recipe`);
  }

  addSauceIngredient(key: string, ingredient: SauceIngredient): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${key}/recipe`, ingredient);
  }
}
