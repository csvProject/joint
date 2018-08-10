import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CsvexportComponent } from './csvexport.component';

const routes: Routes = [
  {
    path:'',
    component:CsvexportComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CsvexportRoutingModule { }
