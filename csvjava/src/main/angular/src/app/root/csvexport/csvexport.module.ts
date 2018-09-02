import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CsvexportRoutingModule } from './csvexport-routing.module';
import { CsvexportComponent } from './csvexport.component';
import { FormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { CsvexportService } from '../../http/csvexport.service';

@NgModule({
  imports: [
    CommonModule,
    CsvexportRoutingModule,

    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [CsvexportComponent],
  providers:[CsvexportService]
})
export class CsvexportModule { }
