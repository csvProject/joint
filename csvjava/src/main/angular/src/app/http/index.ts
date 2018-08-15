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
  findbypfnm : 'platform/findbypfnm', //平台列表
  insertPlatform : 'platform/insert', //新增平台
  delbypfid : 'platform/delbypfid', //删除平台信息
  updatebypfid : 'platform/updatebypfid', //更新平台信息

  /*pfaccount*/
  findbypfid : 'pfaccount/findbypfid', //平台账号列表
  insertPfaccount : 'pfaccount/insert', //平台账号列表
  delbypfacid : 'pfaccount/delbypfacid', //删除帐号
  updatebypfacid : 'pfaccount/updatebypfacid' //更新账号信息
};


export class BaseApiResponseModel {
  code: number;
  msg: string;
  count: number;
  data: any
}
