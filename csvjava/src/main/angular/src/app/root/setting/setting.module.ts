import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SettingComponent } from './setting.component';
import { TemplatesetComponent } from './templateset/templateset.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [SettingComponent, TemplatesetComponent, PlatformacctsetComponent]
})
export class SettingModule { }
