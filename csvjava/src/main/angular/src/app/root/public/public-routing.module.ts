import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path:'csvexport',
    loadChildren: 'app/root/csvexport/csvexport.module#CsvexportModule'
  },
  {
    path:'setting',
    loadChildren: 'app/root/setting/setting.module#SettingModule'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true })],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
