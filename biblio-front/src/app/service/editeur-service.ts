import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Editeur } from '../model/editeur';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/editeur';

  public findAll(): Observable<Editeur[]> {
    return this.http.get<Editeur[]>(this.apiUrl);
  }

  public add(editeur: Editeur): Observable<Editeur> {
    return this.http.post<Editeur>(this.apiUrl, editeur);
  }

  public update(editeur: Editeur): Observable<Editeur> {
    return this.http.put<Editeur>(`${ this.apiUrl }/${ editeur.id }`, editeur);
  }

  public remove(editeur: Editeur): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ editeur.id }`);
  }
}
