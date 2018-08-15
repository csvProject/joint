import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url} from './index';
import { Observable } from "rxjs/index";

@Injectable()
export class TemplatesetService {
  constructor(private http: HttpClient) { }

  getTemplateInfo(pfnm?:string): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.findbypfnm,{
      params:{
        pfnm
      }
    });
  }
}
