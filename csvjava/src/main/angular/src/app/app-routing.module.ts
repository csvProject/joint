import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstComponent } from './root/first/first.component';
import { SecondComponent } from './root/second/second.component';
import { ErrorComponent } from './root/error/error.component';
import {PublicComponent} from "./root/public/public.component";

const routes: Routes = [
  { path: '', redirectTo: 'first', pathMatch: 'full' },
  {
    path:'first',
    component:FirstComponent
  },
  {
    path:'second',
    component:SecondComponent
  },
 /* {
    path:'home',
    loadChildren:'app/root/public/public.module#PublicModule'
  },*/
  {
    path:'home',
    component:PublicComponent
  },
  {
    path:'csvexport',
    loadChildren: 'app/root/csvexport/csvexport.module#CsvexportModule'
  },
  {
    path:'setting',
    loadChildren: 'app/root/setting/setting.module#SettingModule'
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
