import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TokenStorage } from './token.storage';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private token: TokenStorage) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        if (this.token.getToken() != null) {
            request = request.clone({
                setHeaders: {
                    Authorization: this.token.getToken()
                }
            });
            console.log(JSON.stringify(request));
        }

        return next.handle(request);
    }
}