import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Service, Injectable, inject } from '@angular/core';
import { Auteur } from '../../model/auteur';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {
   private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/auteur';

  public findAll(): Observable<Auteur[]> {
    return this.http.get<Auteur[]>(this.apiUrl);
  }

  public add(auteur: Auteur): Observable<Auteur> {
    return this.http.post<Auteur>(this.apiUrl, auteur);
  }

  public update(auteur: Auteur): Observable<Auteur> {
    return this.http.put<Auteur>(`${ this.apiUrl }/${ auteur.id }`, auteur);
  }

  public remove(auteur: Auteur): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ auteur.id }`);
  }

}
