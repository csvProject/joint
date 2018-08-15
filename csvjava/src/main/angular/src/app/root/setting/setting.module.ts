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

@NgModule({
  imports: [
    CommonModule,
    SettingRoutingModule,

    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [SettingComponent, PlatformacctsetComponent, TemplatesetComponent,NzMentionPreviewDemoComponent],
  providers:[
    TemplatesetService,
    PlatformacctService
  ]
})
export class SettingModule { }
