import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingRoutingModule } from './setting-routing.module';
import { SettingComponent } from './setting.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';
import { TemplatesetComponent } from './templateset/templateset.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';
import { TemplatesetService } from '../../http/templateset.service';
import { PlatformacctService } from '../../http/platformacct.service';
import { DatePipe } from '../../pipe/date.pipe';
import { TempeditComponent } from './templateset/tempedit/tempedit.component';
import { FieldEditComponent } from './templateset/fieldedit/fieldedit.component';
import { SortableModule } from 'ngx-bootstrap';
import { FormulasComponent } from './templateset/formulas/formulas.component';

@NgModule({
  imports: [
    CommonModule,
    SettingRoutingModule,
    SortableModule.forRoot(),
    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [SettingComponent,DatePipe, PlatformacctsetComponent, TemplatesetComponent, TempeditComponent, FieldEditComponent, FormulasComponent],
  providers:[
    TemplatesetService,
    PlatformacctService
  ]
})
export class SettingModule { }
