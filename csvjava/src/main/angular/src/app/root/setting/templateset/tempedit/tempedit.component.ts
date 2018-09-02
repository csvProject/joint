import { Component, Input, OnInit } from '@angular/core';
import { CsvTemplateInfo } from "../../../../entity/tempData";
import {TemplatesetService} from "../../../../http/templateset.service";

@Component({
  selector: 'app-tempedit',
  templateUrl: './tempedit.component.html',
  styleUrls: ['./tempedit.component.css']
})
export class TempeditComponent implements OnInit {
  @Input() tempData:CsvTemplateInfo;
  @Input() platList = [];
  @Input() supplierList = [];
  @Input() pTypeList = [];

  accountList = [];

  selectedAccount = '';

  constructor(private service:TemplatesetService) { }

  ngOnInit() {
    console.log(this.tempData)
  }

  provinceAllChange(type,id): void {
    console.log(id);
    if(type == 0){
      if(id == this.tempData.platformId){

      }else{
        this.selectedAccount = '';
        this.getPfaccountInfo(id);
      }
      this.tempData.platformId = id;
    }else if(type == 1){
      this.tempData.pfaccountId = id;
    }else if(type == 2){
      this.tempData.ptypeId = id;
    }else if(type == 3){
      this.tempData.sId = id;
    }
  }

  private getPfaccountInfo(pfid): void {
    this.service.getPfaccountInfo(pfid).subscribe(result=>{
      console.log(result);
      if(result.code == 0){
        this.accountList = result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }

  clickSwitch(switchValue){
    if(switchValue){
      this.tempData.isUse = 0;
    }else{
      this.tempData.isUse = 1;
    }
  }

  clickSwitch2(switchValue){
    if(switchValue){
      this.tempData.headShow = 0;
    }else{
      this.tempData.headShow = 1;
    }
  }
}
