/* 'Barrel' of Http Interceptors */
import {HTTP_INTERCEPTORS, HttpHeaders} from '@angular/common/http';
import { PublicInterceptor } from './publicInterceptor';
import {timeInterval} from "rxjs/internal/operators";

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
  updatebypfacid : 'pfaccount/updatebypfacid', //更新账号信息

  /*templateset*/
  tempfindbycondi:'csvtempinfo/findbycondi', //模板一览
  tempinsert:'csvtempinfo/insert', //插入模板
  tempupdate:'csvtempinfo/updatebyid', //插入模板
  tempdelbyid:'csvtempinfo/delbyid', //删除摸版
  tempcopy:'csvtempinfo/copy', //复制模板

  ptypefindbycondi:'ptype/findbycondi', //产品分类一览
  supplierfindbycondi:'supplier/findbycondi', //供应商一览

  findfieldbycsvtempid:'csvtempdetail/findbycsvtempid', //根据模板ID查询所有字段
  fieldupdatedetailbat:'csvtempdetail/updatedetailbat', //根据模板ID更新字段

  findsyscodebytypecd:'syscode/findbytypecd', //查询匹配字段

  findCsvCustomField:'csvcustomfield/findCsvCustomField', //根据模板所有自定义公式字段


  findproductList:'productcsv/findListbycondi', //查询匹配字段
  exportCSV:'productcsv/export', //导出

  download:'csvfile/download', //下载

  isdelcustomfield:'/', //删除自定义字段校验接口
};


export class BaseApiResponseModel {
  code: number;
  msg: string;
  count: number;
  data: any
}


