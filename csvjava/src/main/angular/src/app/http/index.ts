/* 'Barrel' of Http Interceptors */
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NoopInterceptor } from './noopInterceptor';


/** Http interceptor providers in outside-in order */
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: NoopInterceptor, multi: true },
];


export const url = {
  configUrl : 'assets/config.json'
};
