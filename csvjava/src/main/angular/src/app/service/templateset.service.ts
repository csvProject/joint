import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url} from '../http/index';
import {Observable, Subject} from 'rxjs/index';
import {CsvCustomField, CsvTempBat, CsvTemplateInfo} from '../entity/tempData';

@Injectable()
export class TemplatesetService {
  constructor(private http: HttpClient) { }

  getTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempfindbycondi,csvTemplate);
  }
  insertTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempinsert,csvTemplate);
  }
  copyTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempcopy,csvTemplate);
  }
  updateTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempupdate,csvTemplate);
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

  getSysCodeList(typecd): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findsyscodebytypecd,{
      params:{
        typecd
      }
    });
  }

  getCsvCustomField(csvtempid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findCsvCustomField,{
      params:{
        csvtempid
      }
    });
  }



  fieldListData = new Subject();

  sendFieldListData(list){
    this.fieldListData.next(list);
  }
  getFieldListData(){
    return this.fieldListData.asObservable();
  }

  templateInfo = new Subject<any>();

  sendTemplateInfoData(list){
    this.templateInfo.next(list);
  }
  getTemplateInfoData(){
    return this.templateInfo.asObservable();
  }

  templateInfoForField = new Subject<any>();

  sendTemplateInfoDataForField(data){
    this.templateInfoForField.next(data);
  }
  getTemplateInfoDataForField(){
    return this.templateInfoForField.asObservable();
  }


  isDelCustomField(csvCustomField?:CsvCustomField): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.isdelcustomfield,csvCustomField);
  }
}
