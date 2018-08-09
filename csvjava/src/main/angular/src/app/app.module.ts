import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstComponent } from './root/first/first.component';
import { SecondComponent } from './root/second/second.component';
import { ErrorComponent } from './root/error/error.component';
import { CurrencyUtil } from './util/currencyUtil';
import {PublicComponent} from "./root/public/public.component";
import {HeaderComponent} from "./root/public/header/header.component";
import {MenuComponent} from "./root/public/menu/menu.component";

const customComponentList = [
  FirstComponent,
  SecondComponent,
  PublicComponent,
  HeaderComponent,
  MenuComponent,
];

@NgModule({
  declarations: [
    AppComponent,
    ...customComponentList,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    CurrencyUtil
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
