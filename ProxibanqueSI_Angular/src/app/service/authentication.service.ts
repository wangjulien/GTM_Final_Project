import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import 'rxjs/add/operator/map'
import * as CONST from '../constants';
import { Employee } from '../model/employee';
import { error } from 'util';
import { HttpResponse } from 'selenium-webdriver/http';

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

        const jsonBody: any = { username: login, password: password };

        return this.http.post('http://localhost:8080/login', jsonBody, {observe: 'response'})
            .map(resp => {
                console.log(resp.headers.get('Authorization'));
                localStorage.setItem('userToken', resp.headers.get('Authorization'));
            });
    }

    authAs(login: string){
        return this.http.get<Employee>(this.auth_url + '/' + login).map(employee => {
            localStorage.setItem('currentUser', JSON.stringify(employee));

            this.loggedIn.next(true);
            console.log(JSON.stringify(employee));
            return employee;
        });
    }

    logout() {
        this.loggedIn.next(false);
        localStorage.removeItem('currentUser');
    }
}
