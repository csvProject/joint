import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_I18N, zh_CN,  } from 'ng-zorro-antd';
import { PublicRoutingModule } from './public-routing.module';
import { NotificationComponent } from './notification/notification.component';
import { PublicService } from '../../service/public.service';

/** 配置 angular i18n **/
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { httpInterceptorProviders } from '../../http';
import {InjectorService} from "../../http/injector.service";
import {CurrencyUtil} from "../../util/currencyUtil";
import {LoginService} from "../../service/login.service";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {MenuComponent} from "./menu/menu.component";
import {HeaderComponent} from "./header/header.component";
registerLocaleData(zh);

@NgModule({
  imports: [
    CommonModule,
    PublicRoutingModule,
    FormsModule,
    HttpClientModule,
    NgZorroAntdModule,
    ReactiveFormsModule
  ],
  declarations: [  NotificationComponent,HomeComponent,     LoginComponent,  HeaderComponent,
    MenuComponent      ],
  /** 配置 ng-zorro-antd 国际化 **/
  providers   : [
    httpInterceptorProviders,{ provide: NZ_I18N, useValue: zh_CN },PublicService,
    InjectorService,
    CurrencyUtil,
    LoginService
  ],
  exports:[
    // NgZorroAntdModule
  ]
})
export class PublicModule { }
