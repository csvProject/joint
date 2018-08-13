import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SettingComponent } from './setting.component';
import { TemplatesetComponent } from './templateset/templateset.component';
import { PlatformacctsetComponent } from './platformacctset/platformacctset.component';

const routes: Routes = [
  {
    path:'',
    component:SettingComponent,
    children:[
      {
        path:'templateset',
        component:TemplatesetComponent
      },
      {
        path:'platformacctset',
        component:PlatformacctsetComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingRoutingModule { }
