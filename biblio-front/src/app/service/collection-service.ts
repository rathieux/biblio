import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../model/collection';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/collection';

  public findAll(): Observable<Collection[]> {
    return this.http.get<Collection[]>(this.apiUrl);
  }

  public add(collection: Collection): Observable<Collection> {
    return this.http.post<Collection>(this.apiUrl, collection);
  }

  public update(collection: Collection): Observable<Collection> {
    return this.http.put<Collection>(`${ this.apiUrl }/${ collection.id }`, collection);
  }

  public remove(collection: Collection): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ collection.id }`);
  }
}
