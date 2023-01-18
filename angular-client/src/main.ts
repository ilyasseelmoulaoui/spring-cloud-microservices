import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:4200/',
  realm: 'my-ecom-realm',
  clientId: 'app-product-angular'
});

keycloak.init({ onLoad: 'check-sso' }).then((authenticated: any) => {
  if (authenticated) {
    console.log('User is authenticated');
  } else {
    console.log('User is not authenticated');
  }
});

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
