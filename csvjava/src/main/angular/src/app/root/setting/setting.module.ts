import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingRoutingModule } from './setting-routing.module';
import { SettingComponent } from './setting.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';
import { TemplatesetComponent } from './templateset/templateset.component';

@NgModule({
  imports: [
    CommonModule,
    SettingRoutingModule
  ],
  declarations: [SettingComponent, PlatformacctsetComponent, TemplatesetComponent]
})
export class SettingModule { }
