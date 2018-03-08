import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import 'rxjs/add/operator/map'
import * as CONST from '../constants';
import { Employee } from '../model/employee';
import { error } from 'util';
import { HttpResponse } from 'selenium-webdriver/http';
import { TokenStorage } from './token.storage';

@Injectable()
export class AuthenticationService {

    private auth_url: string = CONST.REST_HOST + '/auth';

    private loggedIn = new BehaviorSubject<boolean>(false);

    get isLoggedIn() {
        return this.loggedIn.asObservable();
    }

    constructor(private http: HttpClient,
                private token: TokenStorage) {
        if (localStorage.getItem('currentUser')) {
            this.loggedIn.next(true);
        }
    }

    auth(login: string, password: string) {
        console.log(`login service ${login} ${password}`);

        const credentials : any = { username: login, password: password };

        return this.http.post('http://localhost:8080/login', credentials, {observe: 'response'})
            .map(resp => {console.log(resp.headers.get('Authorization'));
                this.token.saveToken(resp.headers.get('Authorization'))});
    }

    authAs(login: string) {
        return this.http.get<Employee>(this.auth_url + '/' + login).map(employee => {
            localStorage.setItem('currentUser', JSON.stringify(employee));

            this.loggedIn.next(true);
            console.log(JSON.stringify(employee));
            return employee;
        });
    }

    logout() {
        localStorage.removeItem('currentUser');
        this.loggedIn.next(false);
    }
}
