import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../_models/customer';
import { Order } from '../_models/order';
import { CustomerWithOrders } from '../_models/customer-with-orders';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = `${environment.apiUrl}/customer`;

  constructor(private http: HttpClient) {}

  getCustomerOrdersByNumberSortedBy(number: string, sorted: string): Observable<Order[]> {
    const params = new HttpParams().set('number', number).set('sorted', sorted);
    return this.http.get<Order[]>(this.apiUrl, { params });
  }

  getCustomerOrdersByPhoneSortedBy(phone: string, sorted: string): Observable<Order[]> {
    const params = new HttpParams().set('phone', phone).set('sorted', sorted);
    return this.http.get<Order[]>(this.apiUrl, { params });
  }

  getCustomersByPIB(surname: string = '', name: string = '', patronymic: string = ''): Observable<Customer[]> {
    const params = new HttpParams()
      .set('name', name)
      .set('surname', surname)
      .set('patronymic', patronymic);
    return this.http.get<Customer[]>(`${this.apiUrl}/search`, { params });
  }

  getCustomersAndTheirOrders(): Observable<CustomerWithOrders[]> {
    return this.http.get<CustomerWithOrders[]>(`${this.apiUrl}/order`);
  }

  getCustomersWithOrdersBetweenDates(from: Date, to: Date): Observable<Customer[]> {
    const params = new HttpParams().set('from', from.toISOString().split('T')[0]).set('to', to.toISOString().split('T')[0]);
    return this.http.get<Customer[]>(this.apiUrl, { params });
  }

  getCustomersWithOrderThatHasThisAttribute(attribute: string, value: string): Observable<Customer[]> {
    const params = new HttpParams().set('attribute', attribute).set('value', value);
    return this.http.get<Customer[]>(this.apiUrl, { params });
  }

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  getCustomersByNumber(number: string): Observable<Customer[]> {
    const params = new HttpParams().set('number', number);
    return this.http.get<Customer[]>(`${this.apiUrl}/search`, { params });
  }

  getCustomersByPhone(phone: string): Observable<Customer[]> {
    const params = new HttpParams().set('phone', phone);
    return this.http.get<Customer[]>(`${this.apiUrl}/search`, { params });
  }

  getCustomerByKey(key: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${key}`);
  }

  addCustomer(customer: Partial<Customer>): Observable<void> {
    return this.http.post<void>(this.apiUrl, customer);
  }

  updateCustomer(customer: Partial<Customer>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, customer);
  }

  deleteCustomer(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
