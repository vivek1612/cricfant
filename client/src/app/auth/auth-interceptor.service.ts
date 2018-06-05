import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {AuthService} from './auth.service';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.authService.getAuthToken();
    if (authToken) {
      const modified = req.clone({
        headers: req.headers.set('X-Authorization-Firebase', authToken)
      });
      return next.handle(modified);
    }
    return next.handle(req);
  }

  constructor(private authService: AuthService) {
  }
}
