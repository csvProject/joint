import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BaseApiResponseModel, url} from './index';
import {CsvTemplateInfo} from "../entity/tempData";
import {Observable} from "rxjs/index";

@Injectable()
export class CsvexportService {
  constructor(private http: HttpClient) { }

  findProductList(body): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.findproductList,body);
  }
}
