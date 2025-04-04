import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {firstValueFrom, Observable} from 'rxjs';
import {environment} from '../_environments/environment';
import {Batch} from '../_models/batch';

@Injectable({
  providedIn: 'root',
})
export class BatchService {
  private apiUrl = `${environment.apiUrl}/batch`;

  constructor(private http: HttpClient) {}

  getBatches(): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.apiUrl);
  }

  getBatchByKey(key: string): Observable<Batch> {
    return this.http.get<Batch>(`${this.apiUrl}/${key}`);
  }

  addBatch(batch: Batch): Observable<void> {
    return this.http.post<void>(this.apiUrl, batch);
  }

  deleteBatch(key: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${key}`);
  }
}
