import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { Order } from '../_models/order';
import {Batch} from '../_models/batch';
import {OrderWithCustomerData} from '../_models/order-with-customer-data';
import {OrderWithBatchKeys} from '../_models/order-with-batch-keys';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = `${environment.apiUrl}/order`;

  constructor(private http: HttpClient) {}

  getAllOrdersSortedBy(attribute: string): Observable<OrderWithCustomerData[]> {
    const params = new HttpParams().set('sorted', attribute);
    return this.http.get<OrderWithCustomerData[]>(this.apiUrl, { params });
  }

  getAllOrdersWithOrderNumber(orderNumber: string, attribute: string): Observable<OrderWithCustomerData[]> {
    const params = new HttpParams().set('order', orderNumber).set('sorted', attribute);
    return this.http.get<OrderWithCustomerData[]>(this.apiUrl, { params });
  }

  getBatchesOfOrderSortedBy(orderNumber: string, attribute: string): Observable<Batch[]> {
    const params = new HttpParams()
      .set('number', orderNumber)
      .set('sorted', attribute);
    return this.http.get<Batch[]>(`${this.apiUrl}`, { params });
  }

  getAllOrders(): Observable<OrderWithCustomerData[]> {
    return this.http.get<OrderWithCustomerData[]>(this.apiUrl);
  }

  getOrderByKey(key: string): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${key}`);
  }

  addOrder(order: Partial<OrderWithBatchKeys>): Observable<void> {
    return this.http.post<void>(this.apiUrl, order);
  }

  updateOrder(order: Partial<Order>): Observable<void> {
    return this.http.patch<void>(this.apiUrl, order);
  }

  deleteOrderByKey(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
