import { Injectable } from '@angular/core';
import { AuthRequest } from '../model/auth-request';import { jwtDecode } from 'jwt-decode';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = '';

  public get token(): string {
    const cachedToken = sessionStorage.getItem('token');

    if (cachedToken) {
      if (new Date() > new Date((jwtDecode(cachedToken).exp ?? 0) * 1000)) {
        this.disconnect();
      }
      return cachedToken;
    }
    return this._token;
  }

  constructor(private http: HttpClient) {}

  public isLogged(): boolean {
    return this._token != '';
  }

  public register(request: AuthRequest) {
    return new Observable<void>((observer) =>
      this.http.post('/utilisateur', request).subscribe({
        next: () => {
          observer.next();
        },
        error: (error) => {
          console.log(error);
          observer.error();
        },
      })
    );
  }

  public auth(request: AuthRequest): Observable<void> {
    return new Observable<void>((observer) => {
      this.http.post<string>('/auth', request, { responseType: 'text' }).subscribe({
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
