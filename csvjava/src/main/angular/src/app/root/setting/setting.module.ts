import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingRoutingModule } from './setting-routing.module';
import { SettingComponent } from './setting.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';
import { TemplatesetComponent } from './templateset/templateset.component';
import { NzMentionPreviewDemoComponent } from './platformacctset/nz-mention-preview/nz-mention-preview-demo.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    SettingRoutingModule,

    FormsModule,
    NgZorroAntdModule
  ],
  declarations: [SettingComponent, PlatformacctsetComponent, TemplatesetComponent,NzMentionPreviewDemoComponent]
})
export class SettingModule { }
