import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Conseiller } from '../model/conseiller';
import * as CONST from '../constants';

@Injectable()
export class GerantConseillerService {

  private conseiller_url: string = CONST.REST_HOST + '/conseillers';

  constructor(private http: HttpClient) { }

  getConseillers(): Observable<Conseiller[]> {
    return this.http.get<Conseiller[]>(this.conseiller_url);
  }

  getConseiller(id): Observable<Conseiller> {
    return this.http.get<Conseiller>(this.conseiller_url + '/' + id);
  }

  addConseiller(conseiller): Observable<Conseiller> {
    return this.http.post<Conseiller>(this.conseiller_url, conseiller);
  }

  updateConseiller(conseiller): Observable<Conseiller> {
    return this.http.put<Conseiller>(this.conseiller_url, conseiller);
  }

  deleteConseiller(id): Observable<any> {
    return this.http.delete(this.conseiller_url + '/' + id);
  }

  getConseillersByGerant(id): Observable<Conseiller[]> {
    return this.http.get<Conseiller[]>(this.conseiller_url + '_du_gerant/' + id);
  }

}
