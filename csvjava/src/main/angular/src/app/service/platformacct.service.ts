import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url} from '../http/index';
import { Observable } from "rxjs/index";
import {AccountInfo, PlatInfo} from "../entity/setData";

@Injectable()
export class PlatformacctService {
  constructor(private http: HttpClient) { }

  getPlatInfo(pfnm:string): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findbypfnm,{
      params:{
        pfnm
      }
    });
  }

  getPfaccountInfo(pfid:string): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findbypfid,{
      params:{
        pfid
      }
    });
  }

  delPlatInfoByPfid(pfid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.delbypfid,{
      params:{
        pfid
      }
    });
  }
  delPfaccountInfoByPfacid(pfacid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.delbypfacid,{
      params:{
        pfacid
      }
    });
  }

  upDatePlatInfo(platInfo:PlatInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.updatebypfid,platInfo);
  }

  upDatePfaccountInfo(accountInfo:AccountInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.updatebypfacid,accountInfo);
  }

  insertPlatInfo(platInfo:PlatInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.insertPlatform,platInfo);
  }

  insertPfaccountInfo(accountInfo:AccountInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.insertPfaccount,accountInfo);
  }
}
