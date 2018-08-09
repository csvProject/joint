import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "./header/header.component";
import {PublicComponent} from "./public.component";
import {MenuComponent} from "./menu/menu.component";
import {PublicRoutingModule} from "./public-routing.module";

@NgModule({
  imports: [
    CommonModule,
    PublicRoutingModule
  ],
  declarations: [
    HeaderComponent,
    MenuComponent,
    PublicComponent
  ]
})
export class PublicModule { }
