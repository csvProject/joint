import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PublicComponent} from "./public.component";

const routes: Routes = [
  {
    path: '',
    component: PublicComponent,
    children:[
      { path: '', redirectTo: 'csvexport', pathMatch: 'full' },
      {
        path:'csvexport',
        loadChildren: '../csvexport/csvexport.module#CsvexportModule'
      },
      {
        path:'setting',
        loadChildren: '../setting/setting.module#SettingModule'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
