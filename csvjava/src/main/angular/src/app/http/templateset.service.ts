import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url} from './index';
import { Observable } from 'rxjs/index';
import { CsvTemplateInfo } from '../entity/tempData';

@Injectable()
export class TemplatesetService {
  constructor(private http: HttpClient) { }

  getTemplateInfo(csvTemplate?:CsvTemplateInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.tempfindbycondi,csvTemplate);
  }
}
