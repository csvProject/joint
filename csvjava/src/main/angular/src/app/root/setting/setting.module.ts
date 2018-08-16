import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingRoutingModule } from './setting-routing.module';
import { SettingComponent } from './setting.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';
import { TemplatesetComponent } from './templateset/templateset.component';
import { NzMentionPreviewDemoComponent } from './platformacctset/nz-mention-preview/nz-mention-preview-demo.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';
import { TemplatesetService } from '../../http/templateset.service';
import { PlatformacctService } from '../../http/platformacct.service';
import { DatePipe } from '../../pipe/date.pipe';
import { TempeditComponent } from './templateset/tempedit/tempedit.component';
import { FieldeidComponent } from './templateset/fieldeid/fieldeid.component';

@NgModule({
  imports: [
    CommonModule,
    SettingRoutingModule,

    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [SettingComponent,DatePipe, PlatformacctsetComponent, TemplatesetComponent,NzMentionPreviewDemoComponent, TempeditComponent, FieldeidComponent],
  providers:[
    TemplatesetService,
    PlatformacctService
  ]
})
export class SettingModule { }
