import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from './index';

@Injectable()
export class CsvexportService {
  constructor(private http: HttpClient) { }



  getConfig() {
    return this.http.get(url.configUrl);
  }
}
