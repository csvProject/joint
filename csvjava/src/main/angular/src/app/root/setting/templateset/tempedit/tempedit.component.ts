import { Component, Input, OnInit } from '@angular/core';
import { CsvTemplateInfo } from "../../../../entity/tempData";
import {TemplatesetService} from "../../../../service/templateset.service";

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

  platformNm ;//画面平台下拉控件选中值
  pfaccountNm ;//画面平台账号下拉控件选中值
  ptypeNm ;//画面产品类型下拉控件选中值
  sNm ;//画面供应商下拉控件选中值

  accountList = [];//画面平台账号下拉内容集合


  constructor(private service:TemplatesetService) { }

  ngOnInit() {
    this.service.getTemplateInfoData().subscribe(data=>{
        this.tempData =data;
        console.log(this.tempData);
        if (this.tempData.modalType == 3){
          //是复制画面的时候
          this.platformNm = this.tempData.platformId;
          this.getPfaccountInfo(this.tempData.platformId,true);
          this.ptypeNm = this.tempData.ptypeId;
          this.sNm = this.tempData.sId;
        }else{
          this.platformNm = this.tempData.platformNm;
          this.pfaccountNm = this.tempData.pfaccountNm;
          this.ptypeNm = this.tempData.ptypeNm;
          this.sNm = this.tempData.sNm;
        }
        this.getCsvCustomField(this.tempData.csvtempId==null?(-1):this.tempData.csvtempId);
       })
  }



  provinceAllChange(type,id): void {

    if(type == 0){
      if(id == this.tempData.platformId){

      }else{
        this.getPfaccountInfo(id,false);
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

  private getPfaccountInfo(pfid,init): void {
    this.service.getPfaccountInfo(pfid).subscribe(result=>{
      console.log(result);
      if(result.code == 0){
        this.accountList = result.data;
        this.pfaccountNm = null;
        if (init) {//如果是初始化，则需要绑定值
          for (let i = 0; i < this.accountList.length; i++) {
            if (this.tempData.pfaccountId == this.accountList[i].pfaccountId) {
              this.pfaccountNm = this.tempData.pfaccountId;
              break;
            }
          }
        }


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

  csvCustomFields = [];//模板自定义公式内容集合
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
