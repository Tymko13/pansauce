import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../_environments/environment';
import { Order } from '../_models/order';

@Injectable({ providedIn: 'root' })
export class OrderService {
  private apiUrl = `${environment.apiUrl}/order`;

  constructor(private http: HttpClient) {}

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
