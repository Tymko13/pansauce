import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs';
import {jwtDecode} from 'jwt-decode';
import {AuthRequest} from './auth-request';
import {AuthResponse} from './auth-response';
import {environment} from '../environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly TOKEN_KEY = 'auth-token';

  constructor(private http: HttpClient) {}

  login(credentials: AuthRequest) {
    return this.http.post<AuthResponse>(`${environment.apiUrl}/login`, credentials).pipe(
      tap(response => {
        localStorage.setItem(this.TOKEN_KEY, response.token);
      })
    );
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUserRole(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const decoded: JwtPayload = jwtDecode(token);
      return decoded.role;
    } catch (e) {
      console.error('Invalid token', e);
      return null;
    }
  }

  isTopManager(): boolean {
    return this.getUserRole() === "TOP_MANAGER";
  }

  isSalesManager(): boolean {
    return this.getUserRole() === "SALES_MANAGER";
  }
}

interface JwtPayload {
  username: string;
  password: string;
  role: string;
}
