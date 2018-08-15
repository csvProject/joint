import { Component, OnInit } from '@angular/core';
import {TemplatesetService} from "../../../http/templateset.service";
import {CsvTemplateInfo} from "../../../entity/tempData";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-templateset',
  templateUrl: './templateset.component.html',
  styleUrls: ['./templateset.component.css']
})
export class TemplatesetComponent implements OnInit {
  constructor(private service:TemplatesetService) { }
  cancel(csvTemplateInfo): void {
    this.showModal(0,1,csvTemplateInfo);
  }

  confirm(csvTemplateInfo): void {
    this.showModal(0,1,csvTemplateInfo);
  }

  templateInfo = new CsvTemplateInfo();

  allChecked = false;
  disabledButton = true;
  checkedNumber = 0;
  displayData: Array<{ name: string; age: number; address: string; checked: boolean }> = [];
  operating = false;
  dataSet = [];
  indeterminate = false;

  currentPageDataChange($event: Array<{ name: string; age: number; address: string; checked: boolean }>): void {
    this.displayData = $event;
  }

  refreshStatus(): void {
    const allChecked = this.displayData.every(value => value.checked === true);
    const allUnChecked = this.displayData.every(value => !value.checked);
    this.allChecked = allChecked;
    this.indeterminate = (!allChecked) && (!allUnChecked);
    this.disabledButton = !this.dataSet.some(value => value.checked);
    this.checkedNumber = this.dataSet.filter(value => value.checked).length;
  }

  checkAll(value: boolean): void {
    this.displayData.forEach(data => data.checked = value);
    this.refreshStatus();
  }

  operateData(): void {
    this.operating = true;
    setTimeout(_ => {
      this.dataSet.forEach(value => value.checked = false);
      this.refreshStatus();
      this.operating = false;
    }, 1000);
  }

  ngOnInit(): void {
    let csvTemplateInfo = new CsvTemplateInfo();
    this.getTemplateInfo(csvTemplateInfo);
  }

  getTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    this.service.getTemplateInfo(csvTemplateInfo).subscribe(result=>{
      if(result.code == 0){
        this.dataSet = result.data == null?[]:result.data;
        this.dataSet.forEach(value => value.checked = false);
      }else if(result.code == 1){

      }
    })
  }

  titleList=['新增模板','编辑模板'];
  modalType;
  isVisible = false;
  isConfirmLoading = false;

  delete(type,data){
    type==1?{

    }:{

    };
  }
  showModal(i,type,csvTemplateInfo?:CsvTemplateInfo): void {
    this.isVisible = true;
    this.modalType = type;
    if(type == 0){
      if(csvTemplateInfo == undefined){
        this.templateInfo = new CsvTemplateInfo()
      } else{
        this.templateInfo = Object.create(csvTemplateInfo);
        this.templateInfo.csvtempId = null;
      }
    }else if(type == 1){
      this.templateInfo = csvTemplateInfo;
    }
  }

  handleOk(modalType): void {
    this.isConfirmLoading = true;
    setTimeout(() => {
      this.isVisible = false;
      this.isConfirmLoading = false;
    }, 3000);
  }

  handleCancel(): void {
    this.isVisible = false;
  }
}
