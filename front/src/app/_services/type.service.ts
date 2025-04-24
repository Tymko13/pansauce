import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Type } from '../_models/type';
import { Sauce } from '../_models/sauce';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  private apiUrl = `${environment.apiUrl}/type`;

  constructor(private http: HttpClient) {}

  getSaucesWithTypeNumberSortedBy(number: string, sorted: string): Observable<Sauce[]> {
    const params = new HttpParams().set('number', number).set('sorted', sorted);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getSaucesWithTypeNameSortedBy(name: string, sorted: string): Observable<Sauce[]> {
    const params = new HttpParams().set('name', name).set('sorted', sorted);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  findAllTypes(): Observable<Type[]> {
    return this.http.get<Type[]>(this.apiUrl);
  }

  getTypeByKey(key: string): Observable<Type> {
    return this.http.get<Type>(`${this.apiUrl}/${key}`);
  }

  addType(type: Type): Observable<void> {
    return this.http.post<void>(this.apiUrl, type);
  }

  deleteType(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }

  updateType(type: Type): Observable<void> {
    return this.http.patch<void>(this.apiUrl, type);
  }
}

