import { Injectable } from '@angular/core';
import {BaseApiResponseModel, url} from "../http/index";
import { Observable,Subject } from 'rxjs/index';
import { UserInfo } from '../entity/UserInfo';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class LoginService {
  private loginSubject = new Subject();

  constructor(private http: HttpClient) { }

  login(userInfo:UserInfo): Observable<BaseApiResponseModel> { //登录接口
    return this.http.post<BaseApiResponseModel>(url.user_login, userInfo);
  }

}
