import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstComponent } from './root/first/first.component';
import { SecondComponent } from './root/second/second.component';
import { ErrorComponent } from './root/error/error.component';
import { CurrencyUtil } from './util/currencyUtil';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { httpInterceptorProviders } from './http';
import { HttpClientModule } from '@angular/common/http';

const customComponentList = [
  FirstComponent,
  SecondComponent,
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
    CurrencyUtil,
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
