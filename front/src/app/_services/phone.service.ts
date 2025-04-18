import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { Phone } from '../_models/phone';

@Injectable({ providedIn: 'root' })
export class PhoneService {
  private apiUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  addPhoneToCustomer(key: string, phone: string): Observable<void> {
    const newPhone: Phone = {customerNumber: key, phoneNumber: phone};
    return this.http.post<void>(`${this.apiUrl}/customer/${key}/phone`, newPhone);
  }

  getCustomerPhones(key: string): Observable<Phone[]> {
    return this.http.get<Phone[]>(`${this.apiUrl}/customer/${key}/phone`);
  }

  deleteCustomerPhone(phone: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/phone/${phone}`);
  }

  existsPhone(phone: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/phone/${phone}`);
  }
}
