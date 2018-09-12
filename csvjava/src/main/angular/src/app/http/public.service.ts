import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from './index';
import { environment } from '../../environments/environment';
import {Subject} from "rxjs/index";

@Injectable()
export class PublicService {
  constructor(private http: HttpClient) { }

  downloadFile(fileName) {
    let getUrl = environment.uri+ url.download+'?fileName='+fileName;
    let a = document.createElement('a');
    document.body.appendChild(a);
    a.setAttribute('style', 'display:none');
    a.setAttribute('href', getUrl);
    a.setAttribute('download', 'zip文件');
    a.click();
    document.body.removeChild(a);
    //释放URL地址
    URL.revokeObjectURL(getUrl);
  }

  productListSel:any = [];

  setProductListSel(data){
    this.productListSel = data
  }
  getProductListSel():any[]{
    return this.productListSel;
  }
}
