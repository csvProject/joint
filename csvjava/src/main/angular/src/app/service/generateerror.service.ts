import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseApiResponseModel, url} from "../http/index";
import {Observable} from "rxjs/index";
import {CsvTemplateInfo} from "../entity/tempData";

@Injectable()
export class GenerateerrorService {

  constructor(private http: HttpClient) { }

  findErrOrderNo(body): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.findErrOrderNo,body);
  }

  updDelFlag(generateerrorid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.updDelFlag,{
      params:{
        generateerrorid
      }
    });
  }
}
