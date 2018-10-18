import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseApiResponseModel, url} from "./index";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class GenerateerrorService {

  constructor(private http: HttpClient) { }

  findProductList(body): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.findproductList,body);
  }
}
