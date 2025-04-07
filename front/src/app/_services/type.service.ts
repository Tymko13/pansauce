import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Type } from '../_models/type';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private apiUrl = `${environment.apiUrl}/type`;

  constructor(private http: HttpClient) {}

  getSaucesWithTypeNumberSortedBy(typeNumber: string, attribute: string): Observable<Sauce[]> {
    const params = new HttpParams().set('number', typeNumber).set('sorted', attribute);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getSaucesWithTypeNameSortedBy(typeName: string, attribute: string): Observable<Sauce[]> {
    const params = new HttpParams().set('name', typeName).set('sorted', attribute);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getAllTypes(): Observable<Type[]> {
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
}
