import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { KeycloakService } from 'keycloak-angular';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private keycloakService: KeycloakService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return from(Promise.resolve(this.keycloakService.isLoggedIn())).pipe(
      switchMap(loggedIn => {
        if (!loggedIn) {
          console.warn('AuthInterceptor: User not logged in, sending request without token');
          return next.handle(request);
        }
        return from(this.keycloakService.getToken()).pipe(
          switchMap(token => {
            console.log('AuthInterceptor: Adding bearer token to request');
            const clonedRequest = request.clone({
              setHeaders: {
                Authorization: `Bearer ${token}`
              }
            });
            return next.handle(clonedRequest);
          })
        );
      })
    );
  }
}

