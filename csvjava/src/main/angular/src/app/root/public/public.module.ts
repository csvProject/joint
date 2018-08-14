import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { PublicRoutingModule } from './public-routing.module';
import { PublicComponent } from './public.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { NotificationComponent } from './notification/notification.component';
import { PublicService } from '../../http/public.service';

/** 配置 angular i18n **/
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { httpInterceptorProviders } from '../../http';
registerLocaleData(zh);

@NgModule({
  imports: [
    CommonModule,
    PublicRoutingModule,
    FormsModule,
    HttpClientModule,
    /** 导入 ng-zorro-antd 模块 **/
    NgZorroAntdModule
  ],
  declarations: [PublicComponent, HeaderComponent, MenuComponent,NotificationComponent],
  /** 配置 ng-zorro-antd 国际化 **/
  providers   : [ httpInterceptorProviders,{ provide: NZ_I18N, useValue: zh_CN },PublicService ],
  exports:[
    // NgZorroAntdModule
  ]
})
export class PublicModule { }
