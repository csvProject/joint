import { Component, OnInit, TemplateRef } from '@angular/core';
import { NzNotificationService} from "ng-zorro-antd";
import { NzNotificationDataOptions } from "ng-zorro-antd/src/notification/nz-notification.definitions";
import {CsvexportService} from "../../http/csvexport.service";
import {b} from "@angular/core/src/render3";

@Component({
  selector: 'app-csvexport',
  templateUrl: './csvexport.component.html',
  styleUrls: ['./csvexport.component.css']
})
export class CsvexportComponent implements OnInit {

  constructor(private notification: NzNotificationService,private service:CsvexportService) { }
  ngOnInit(): void {
    this.findProductList(null);
  }

  private findProductList(body){
    this.service.findProductList(body).subscribe(result=>{
      if(result.code == 0){
        this.dataSet = result.data==null?[]:result.data;
        for (let a of this.dataSet){
          a.checked = false
        }
      }else {

      }
    })
  }
  allChecked = false;
  disabledButton = true;
  checkedNumber = 0;
  displayData= [];
  operating = false;
  dataSet = [];
  indeterminate = false;
  checkedData=[];

  currentPageDataChange($event): void {
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

  operateData(template: TemplateRef<{}>): void {
    this.checkedData = this.getCheckedData();
    this.operating = true;
    setTimeout(_ => {
      this.dataSet.forEach(value => value.checked = false);
      this.refreshStatus();
      this.operating = false;
      this.createBasicNotification(template);
    }, 1000);
  }

  getCheckedData(){
    let dataList = [];
    this.dataSet.forEach(value => {value.checked?dataList.push(value):'';});
    return dataList;
  }


  createBasicNotification(template: TemplateRef<{}>): void {
    this.notification.config({
      nzPlacement:'topLeft'
    });
    let option:NzNotificationDataOptions = { nzDuration:0, };
    this.notification.template(template,option);
  }


}
