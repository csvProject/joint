import { Injectable, Injector } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders, HttpResponse, HttpErrorResponse, HttpClient
} from '@angular/common/http';

import { mergeMap, catchError } from 'rxjs/operators';
import {
   environment } from '../../environments/environment';
import { Observable, of } from 'rxjs/index';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import {LocalStorageService} from "../service/localStorage.service";

/** Pass untouched request through to the next request handler. */
@Injectable()
export class PublicInterceptor implements HttpInterceptor {
  constructor(private injector: Injector,
              private lg:LocalStorageService,
              private router: Router,){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let headerOptions={'Content-Type': 'application/json',
      'Token': this.lg.get('token')?this.lg.get('token'):"",
      'Token-Userid':this.lg.get("userInfo")?this.lg.get("userInfo").id+"":""};

    const authReq = req.clone({
      url: environment.uri+req.url,
      //headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      headers: new HttpHeaders(headerOptions)
    });
    // send the cloned, 'secure' request to the next handler.
    return next.handle(authReq).pipe(
      mergeMap((event: HttpEvent<any>) => {

        if (event instanceof HttpResponse ){
          //登录、本地json取值的不重置token，其他调接口则重置token，以操作画面了则重新计算token时间
          if (event.url.indexOf("/ifcUser/login")<0
            && event.url.indexOf("assets/")<0) {
            let new_token = event.headers.get('new_token');
            console.log("next token********" ,  this.lg.get('token'));
            console.log("next new_token********" ,  new_token);
            if(this.lg.get('token')!=null && new_token!=null){
              this.lg.set('token',new_token);
            }
          }
        }

        // 若一切都正常，则后续操作
        return of(event);
      }),
      catchError((err: HttpErrorResponse) => this.handleData(err)),
    );
  }

  // 允许统一对请求错误处理，这是因为一个请求若是业务上错误的情况下其HTTP请求的状态是200的情况下需要
  private handleData(
    event: HttpResponse<any> | HttpErrorResponse | any,
  ): Observable<any> {
    // 业务处理：一些通用操作
    if(event.body != null && event.body.code < 0){
      this.msg.error(event.body.msg);
    }

    if(event.status == 500){

    }else{
      this.lg.set('token',null);
      this.lg.set('userInfo',null);
      this.goTo('/ifcUser/login');
    }

    /*switch (event.status) {
      case 200:

        break;
      case 401: // 未登录状态码
        this.goTo('/ifcUser/login');
        break;
      case 403:
      case 404:
      /!*case 500:
        this.goTo(`/${event.status}`);
        break;*!/
      default:
        if (event instanceof HttpErrorResponse) {
          console.warn(
            '未可知错误，大部分是由于后端不支持CORS或无效配置引起',
            event,
          );
          this.msg.error(event.message);
        }
        break;
    }*/
    return of(event);
  }

  private goTo(url: string) {
    this.router.navigate(['home/login']);
  }

  get msg(): NzMessageService {
    return this.injector.get(NzMessageService);
  }
}
