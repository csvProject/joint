import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url} from './index';
import { Observable } from 'rxjs/index';
import {CsvTempBat, CsvTemplateInfo} from '../entity/tempData';

@Injectable()
export class TemplatesetService {
  constructor(private http: HttpClient) { }

  getTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempfindbycondi,csvTemplate);
  }
  insertTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempinsert,csvTemplate);
  }

  fieldUpdate(csvTempBat?:CsvTempBat): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.fieldupdatedetailbat,csvTempBat);
  }

  getPtypeList(): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.ptypefindbycondi,{});
  }
  getSupplierList(): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.supplierfindbycondi,{});
  }

  getPlatInfo(pfnm:string): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findbypfnm,{
      params:{
        pfnm
      }
    });
  }

  deletePlatInfo(csvtempid:string): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.tempdelbyid,{
      params:{
        csvtempid
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

  getFieldListCsvtempid(csvtempid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findfieldbycsvtempid,{
      params:{
        csvtempid
      }
    });
  }
}
