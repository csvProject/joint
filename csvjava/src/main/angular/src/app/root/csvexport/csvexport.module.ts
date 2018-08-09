import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CsvexportRoutingModule } from './csvexport-routing.module';
import { CsvexportComponent } from './csvexport.component';

@NgModule({
  imports: [
    CommonModule,
    CsvexportRoutingModule
  ],
  declarations: [CsvexportComponent]
})
export class CsvexportModule { }
