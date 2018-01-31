import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
 
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available
        let userToken = JSON.parse(localStorage.getItem('userToken'));
        console.log(JSON.stringify(userToken));

        if (userToken) {
            request = request.clone({
                setHeaders: {
                    Authorization: userToken
                }
            });
        }
 
        return next.handle(request);
    }
}