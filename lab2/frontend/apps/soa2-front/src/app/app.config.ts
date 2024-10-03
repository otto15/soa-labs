import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { appRoutes } from './app.routes';
import { provideAnimations } from '@angular/platform-browser/animations';
import { NG_EVENT_PLUGINS } from '@taiga-ui/event-plugins';
import { API_PERSON_PREFIX, API_PRODUCTS_PREFIX, BASE_URL, } from "@soa2-front/shared";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(appRoutes),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimations(),
    NG_EVENT_PLUGINS,
    {
      provide: BASE_URL,
      useValue: 'http://localhost:8080'
    },
    {
      provide: API_PRODUCTS_PREFIX,
      useValue: 'products'
    },
    {
      provide: API_PERSON_PREFIX,
      useValue: 'person'
    }
  ],
};
