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

  getAllTypes(): Observable<Type[]> {
    return this.http.get<Type[]>(this.apiUrl);
  }

  getAllTypesSortedBy(attribute: string): Observable<Type[]> {
    const params = new HttpParams().set('sorted', attribute);
    return this.http.get<Type[]>(this.apiUrl, { params });
  }

  getTypesWithNumberPrefixSortedBy(prefix: string, attribute: string): Observable<Type[]> {
    const params = new HttpParams()
      .set('number', prefix)
      .set('sorted', attribute);
    return this.http.get<Type[]>(`${this.apiUrl}/search`, { params });
  }

  getTypesWithNamePrefixSortedBy(prefix: string, attribute: string): Observable<Type[]> {
    const params = new HttpParams()
      .set('name', prefix)
      .set('sorted', attribute);
    return this.http.get<Type[]>(`${this.apiUrl}/search`, { params });
  }

  getSaucesWithTypeNumberSortedBy(number: string, attribute: string): Observable<Sauce[]> {
    const params = new HttpParams()
      .set('number', number)
      .set('sorted', attribute);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getSaucesWithTypeNameSortedBy(name: string, attribute: string): Observable<Sauce[]> {
    const params = new HttpParams()
      .set('name', name)
      .set('sorted', attribute);
    return this.http.get<Sauce[]>(this.apiUrl, { params });
  }

  getTypeByKey(key: string): Observable<Type> {
    return this.http.get<Type>(`${this.apiUrl}/${key}`);
  }

  addType(type: Partial<Type>): Observable<void> {
    return this.http.post<void>(this.apiUrl, type);
  }

  updateType(type: Partial<Type>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, type);
  }

  deleteType(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}


