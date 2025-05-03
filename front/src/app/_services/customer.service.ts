import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import { Customer } from '../_models/customer';
import { Order } from '../_models/order';
import { CustomerWithOrders } from '../_models/customer-with-orders';
import { environment } from '../environment';
import {CustomerWithOrdersAndBatches} from '../_models/customer-with-orders-and-batches';
import {CustomerOrderData} from '../_models/customer-order-data';
import {SauceWithSalesCount} from '../_models/sauce-with-sales-count';
import {Sauce} from '../_models/sauce';

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

  // getCustomerFavouriteSauce(key: string): Observable<Sauce[]> {
  //   return this.http.get<SauceWithSalesCount>(`${this.apiUrl}/customer/${key}/favourite_sauce`)
  //     .pipe(
  //       map(sauceWithSales => {
  //         const sauce: Sauce = {
  //           number: sauceWithSales.
  //           name: string;
  //           shelfLife: number;
  //           weight: number;
  //           cost: number;
  //           typeNumber: string;
  //           typeName: string;
  //         };
  //         return [sauce];
  //       })
  //     );
  // }

  getCustomerFavouriteSauce(key: string): Observable<SauceWithSalesCount> {
    return this.http.get<SauceWithSalesCount>(`http://localhost:8080/customer/${key}/favourite_sauce`);
  }

  getCustomersWithOrdersBetweenDates(from: Date, to: Date): Observable<Customer[]> {
    const params = new HttpParams()
      .set('from', from.toISOString().split('T')[0])
      .set('to', to.toISOString().split('T')[0]);
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

  getCustomersWhoOrderedAllTypes(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.apiUrl}/all_types`);
  }

  getCustomersWhoOrderedOnlyOneType(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.apiUrl}/one_type`);
  }

  getCustomerByKey(key: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${key}`);
  }

  addCustomer(customer: Partial<CustomerWithOrdersAndBatches>): Observable<void> {
    return this.http.post<void>(this.apiUrl, customer);
  }

  updateCustomer(customer: Partial<Customer>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, customer);
  }

  deleteCustomer(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }

  getCustomersOrderData(): Observable<CustomerOrderData[]> {
    return this.http.get<CustomerOrderData[]>(`${environment.apiUrl}/customer/orders/data`);
  }
}
