import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './ui/products/products.component';
import { CustomersComponent } from './ui/customers/customers.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { OrdersComponent } from './ui/orders/orders.component';
import { OrderDetailsComponent } from './ui/order-details/order-details.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'bdcc-realm',
        clientId: 'ecom-client-ang'
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe: false,
        enableLogging: true,
        flow: 'implicit'
      },
      loadUserProfileAtStartUp: true,
      bearerExcludedUrls: ['/assets']
    }).catch(err => {
      console.error('Erreur INIT Keycloak (JSON):', JSON.stringify(err));
      console.error('Erreur INIT Keycloak (Raw):', err);
      return Promise.resolve();
    });
}
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CustomersComponent,
    OrdersComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule, KeycloakAngularModule
  ],
  providers: [
    { provide: APP_INITIALIZER, useFactory: initializeKeycloak, multi: true, deps: [KeycloakService] },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
