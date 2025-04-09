import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { Customer } from '../_models/customer';
import {Injectable} from '@angular/core';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private apiUrl = `${environment.apiUrl}/customer`;

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  getCustomerByKey(key: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${key}`);
  }

  addCustomer(customer: Customer): Observable<void> {
    return this.http.post<void>(this.apiUrl, customer);
  }

  deleteCustomer(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
