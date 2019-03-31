import { Component, OnInit } from '@angular/core';
import {PublicService} from "../../../service/public.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  selected = 0;  //0 csvexport  1 templateset  2 platformacctset

  constructor(private service:PublicService) { }
  loading:any = false;
  ngOnInit() {
    this.service.getLoading().subscribe(data=>{
      this.loading = data;
    })

    let hash = window.location.hash;
    if (hash.indexOf("csvexport")>=0){
      this.selected = 0;
    }else if(hash.indexOf("templateset")>=0){
      this.selected = 1;
    }else if(hash.indexOf("platformacctset")>=0){
      this.selected = 2;
    }else if(hash.indexOf("generateerror")>=0){
      this.selected = 3;
    }

  }

}
