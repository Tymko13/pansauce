import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Phone } from '../_models/phone';

@Injectable({ providedIn: 'root' })
export class PhoneService {
  private apiUrl = `${environment.apiUrl}/customer`;

  constructor(private http: HttpClient) {}

  addPhoneToCustomer(key: string, phone: Phone): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${key}/phone`, phone);
  }

  getCustomerPhones(key: string): Observable<Phone[]> {
    return this.http.get<Phone[]>(`${this.apiUrl}/${key}/phone`);
  }
}
