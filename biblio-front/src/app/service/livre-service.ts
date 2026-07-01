import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Livre } from '../model/livre';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/livre';

  public findAll(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiUrl);
  }

  public findByTitre(recherche: string): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${ this.apiUrl }/recherche`, {
      params: { recherche },
    });
  }

  public add(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(this.apiUrl, livre);
  }

  public update(livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(`${ this.apiUrl }/${ livre.id }`, livre);
  }

  public remove(livre: Livre): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ livre.id }`);
  }
}
