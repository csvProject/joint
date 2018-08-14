import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from './index';

@Injectable()
export class TemplatesetService {
  constructor(private http: HttpClient) { }



  getTemplateInfo(pfnm:string) {
    return this.http.get(url.findbypfnm,{
      params:{pfnm}
    });
  }
}
