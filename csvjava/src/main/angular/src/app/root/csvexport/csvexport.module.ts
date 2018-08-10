import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CsvexportRoutingModule } from './csvexport-routing.module';
import { CsvexportComponent } from './csvexport.component';
import { FormsModule } from '@angular/forms';
import { NgZorroAntdModule } from 'ng-zorro-antd';

@NgModule({
  imports: [
    CommonModule,
    CsvexportRoutingModule,

    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [CsvexportComponent]
})
export class CsvexportModule { }
