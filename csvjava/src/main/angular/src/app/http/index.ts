/* 'Barrel' of Http Interceptors */
import {HTTP_INTERCEPTORS, HttpHeaders} from '@angular/common/http';
import { PublicInterceptor } from './publicInterceptor';

/** Http interceptor providers in outside-in order */
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: PublicInterceptor, multi: true },
];


export const url = {
  configUrl : 'assets/config.json',

  /*platform*/
  findbypfnm : 'platform/findbypfnm', //平台详情列表
};
