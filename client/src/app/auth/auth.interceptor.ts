import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {throwError} from "rxjs/index";
import {tap} from 'rxjs/operators';
import {catchError} from "rxjs/internal/operators";
import {JwtHelperService} from "@auth0/angular-jwt";
import {ActivatedRoute, Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  private blacklist = [
    {method: 'POST', path: '/oauth/token'}
  ];

  constructor(private auth: AuthService, private router: Router, private route: ActivatedRoute) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    console.log(`intercepting request ${request.method} ${request.url}`);
    for (let item of this.blacklist) {
      if (request.method === item.method && request.url.endsWith(item.path)) {
        console.log(`skipping blacklisted url ${request.url}`);
        return this.doNext(next, request);
      }
    }
    let access_token = this.auth.getAccessToken();
    if (access_token) {
      console.log("access token found");
      request = this.setAuthHeader(request);
      console.log(request.headers.get('Authorization'));
    }
    return this.doNext(next, request);
  }

  private doNext(next: HttpHandler, request: HttpRequest<any>) {
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {
        console.log(" any event");
        console.log(event);
        if (event instanceof HttpResponse) {
          console.log("response event");
          console.log(event);
        }
      }),
      catchError((err) => {
        console.log("any error")
        console.log(err);
        if (err instanceof HttpErrorResponse) {
          console.log("response error");
          if (err.status === 401) {
            console.log("unauthorized error");
            console.log(err);
          }
        }
        return throwError(err);
      })
    );
  }

  private setAuthHeader(request: HttpRequest<any>): HttpRequest<any> {
    return request.clone({
      headers: request.headers.set('Authorization', `Bearer ${this.auth.getAccessToken()}`)
    });
  }
}
