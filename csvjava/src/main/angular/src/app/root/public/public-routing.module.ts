import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {MenuComponent} from "./menu/menu.component";
import {AuthGuard} from "../../auth.guard";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: LoginComponent
      },
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  },
  {
    path: 'lfs',
    /*canActivate: [AuthGuard],*/
    component: MenuComponent,
    children:[
      /*{ path: '', redirectTo: 'csvexport', pathMatch: 'full' },*/
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
