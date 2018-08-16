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

  selectedPlat = '';
  accountList = [];

  platformId = null;
  pfaccountId = null;
  ptypeId = null;
  sId = null;

  selectedAccount = '';

  constructor(private service:TemplatesetService) { }

  ngOnInit() {
    console.log(this.tempData)
  }

  provinceAllChange(type,id): void {
    if(type == 0){
      if(id == this.platformId){

      }else{
        this.selectedAccount = '';
        this.getPfaccountInfo(id);
      }
      this.platformId = id;
    }else if(type == 1){
      this.pfaccountId = id;
    }else if(type == 2){
      this.ptypeId = id;
    }else if(type == 3){
      this.sId = id;
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

  switchValue = false;
  clickSwitch(switchValue){
    this.switchValue = !switchValue;
  }
}
