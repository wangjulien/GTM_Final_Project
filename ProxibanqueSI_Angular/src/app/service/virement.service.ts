import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Virement } from '../model/virement';
import * as CONST from '../constants';

@Injectable()
export class VirementService {

  private client_url: string = CONST.REST_HOST + '/virements';

  constructor(private http: HttpClient) { }

  doVirement(virement): Observable<Virement>  {
    return this.http.post<Virement>(this.client_url, virement);
  }
}
