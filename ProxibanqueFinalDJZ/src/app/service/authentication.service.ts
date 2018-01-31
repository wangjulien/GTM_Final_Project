import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import 'rxjs/add/operator/map'
import * as CONST from '../constants';

@Injectable()
export class AuthenticationService {

    private auth_url: string = CONST.REST_HOST + '/auth';

    private loggedIn = new BehaviorSubject<boolean>(false);

    get isLoggedIn() {
        return this.loggedIn.asObservable();
    }

    constructor(private http: HttpClient) {
        if (localStorage.getItem('currentUser')) {
            this.loggedIn.next(true);
        }
    }

    auth(login: string, password: string) {
        console.log(`login service ${login} ${password}`);

        const jsonBody: any = { type: 'Conseiller', login: login, password: password };

        return this.http.post<any>(this.auth_url, jsonBody)
            .map(user => {
                if (user && user.token) {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.loggedIn.next(true);
                }
                console.log(JSON.stringify(user));
                return user;
            });
    }

    logout() {
        this.loggedIn.next(false);
        localStorage.removeItem('currentUser');
    }
}
