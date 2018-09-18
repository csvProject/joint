import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorComponent } from './root/error/error.component';
import { CurrencyUtil } from './util/currencyUtil';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {InjectorService} from "./http/injector.service";

const customComponentList = [

];

@NgModule({
  declarations: [
    AppComponent,
    ...customComponentList,
    ErrorComponent
  ],
  imports: [
    // BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    // import HttpClientModule after BrowserModule.
    HttpClientModule,
  ],
  providers: [
    InjectorService,
    CurrencyUtil
  ],
  bootstrap: [AppComponent],
  exports:[

  ]
})
export class AppModule { }
