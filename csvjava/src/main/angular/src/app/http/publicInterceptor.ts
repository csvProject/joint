import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class PublicInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const secureReq = req.clone({
      url: environment.uri+req.url,
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
    // send the cloned, 'secure' request to the next handler.
    return next.handle(secureReq);
  }
}
