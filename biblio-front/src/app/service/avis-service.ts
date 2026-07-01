import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Avis } from '../model/avis';

@Injectable({
  providedIn: 'root',
})
export class AvisService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/avis';

  public findAll(): Observable<Avis[]> {
    return this.http.get<Avis[]>(this.apiUrl);
  }

  public add(avis: Avis): Observable<Avis> {
    return this.http.post<Avis>(this.apiUrl, avis);
  }

  public update(avis: Avis): Observable<Avis> {
    return this.http.put<Avis>(`${this.apiUrl}/${avis.id}`, avis);
  }

  public remove(avis: Avis): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${avis.id}`);
  }
}
