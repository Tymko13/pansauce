import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Order } from '../_models/order';
import {Batch} from '../_models/batch';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = `${environment.apiUrl}/order`;

  constructor(private http: HttpClient) {}

  getAllOrdersSortedBy(attribute: string): Observable<Order[]> {
    const params = new HttpParams().set('sorted', attribute);
    return this.http.get<Order[]>(this.apiUrl, { params });
  }

  getBatchesOfOrderSortedBy(orderNumber: string, attribute: string): Observable<Batch[]> {
    const params = new HttpParams()
      .set('number', orderNumber)
      .set('sorted', attribute);
    return this.http.get<Batch[]>(`${this.apiUrl}`, { params });
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
  }

  getOrderByKey(key: string): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${key}`);
  }

  addOrder(order: Order): Observable<void> {
    return this.http.post<void>(this.apiUrl, order);
  }

  deleteOrderByKey(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
