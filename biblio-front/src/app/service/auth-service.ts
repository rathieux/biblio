import { Injectable } from '@angular/core';
import { AuthRequest } from '../model/auth-request';
import { Observable } from 'rxjs';
import { AuthResponse } from '../model/auth-response';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = sessionStorage.getItem('token') ?? '';

  public get token(): string {
    return this._token;
  }

  constructor(private http: HttpClient) {}

  public isLogged(): boolean {
    return this._token != '';
  }

  public register(request: AuthRequest) {
    this.http.post('/auth/register', request).subscribe();
  }

  public auth(request: AuthRequest): Observable<void> {
    return new Observable<void>((observer) => {
      this.http
        .post<string>('/auth', request, { responseType: 'text' })
        .subscribe({
          next: (resp) => {
            this._token = resp;
            sessionStorage.setItem('token', resp);
            observer.next();
          },

          error: (error) => {
            console.log(error);
            observer.error();
          },
        });
    });
  }

  public disconnect() {
    this._token = '';
    sessionStorage.removeItem('token');
  }
}
