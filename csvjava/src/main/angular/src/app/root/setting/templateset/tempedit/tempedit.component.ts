import { Component, Input, OnInit } from '@angular/core';
import { CsvTemplateInfo } from "../../../../entity/tempData";
import {TemplatesetService} from "../../../../http/templateset.service";

@Component({
  selector: 'app-tempedit',
  templateUrl: './tempedit.component.html',
  styleUrls: ['./tempedit.component.css']
})
export class TempeditComponent implements OnInit {
  tempData:CsvTemplateInfo = new CsvTemplateInfo();
  @Input() platList = [];
  @Input() supplierList = [];
  @Input() pTypeList = [];

  platformNm = '';
  pfaccountNm = '';
  ptypeNm = '';
  sNm = '';

  accountList = [];


  constructor(private service:TemplatesetService) { }

  ngOnInit() {
    this.service.getTemplateInfoData().subscribe(data=>{
        this.tempData =data;
        console.log(this.tempData);
        this.platformNm = this.tempData.platformNm;
        this.pfaccountNm = this.tempData.pfaccountNm;
        this.ptypeNm = this.tempData.ptypeNm;
        this.sNm = this.tempData.sNm;
        this.getCsvCustomField(this.tempData.csvtempId==null?(-1):this.tempData.csvtempId);
       })
  }



  provinceAllChange(type,id): void {
    this.tempData.platformNm = this.platformNm;
    this.tempData.pfaccountNm = this.pfaccountNm;
    this.tempData.ptypeNm = this.ptypeNm;
    this.tempData.sNm = this.sNm;
    if(type == 0){
      if(id == this.tempData.platformId){

      }else{
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

  csvCustomFields = [];
  private getCsvCustomField(csvtempid){
    this.service.getCsvCustomField(csvtempid).subscribe(result=>{
      if(result.code == 0){
        this.csvCustomFields = result.data == null?[]:result.data;
        this.tempData.csvCustomFieldDtoList = this.csvCustomFields;
      }else{
        console.error(result.msg)
      }
    })
  }
}
