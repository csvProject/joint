import { Component, OnInit } from '@angular/core';
import {TemplatesetService} from "../../../http/templateset.service";

@Component({
  selector: 'app-templateset',
  templateUrl: './templateset.component.html',
  styleUrls: ['./templateset.component.css']
})
export class TemplatesetComponent implements OnInit {
  pfnm:string='';

  constructor(private service:TemplatesetService) { }

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
    this.getTemplateInfo(this.pfnm);
    for (let i = 0; i < 46; i++) {
      this.dataSet.push({
        name   : `Edward King ${i}`,
        age    : 32,
        address: `London, Park Lane no. ${i}`,
        checked: false
      });
    }
  }

  getTemplateInfo(pfnm){
    this.service.getTemplateInfo(pfnm).subscribe(data=>{
      console.log(data);
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
  showModal(i,type): void {
    this.isVisible = true;
    this.modalType = type;
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
