import { Component, OnInit, TemplateRef } from '@angular/core';
import { NzNotificationService} from "ng-zorro-antd";
import { NzNotificationDataOptions } from "ng-zorro-antd/src/notification/nz-notification.definitions";
import {CsvexportService} from "../../http/csvexport.service";
import {debounceTime, map, switchMap} from "rxjs/internal/operators";
import {BehaviorSubject, Observable} from "rxjs/index";

@Component({
  selector: 'app-csvexport',
  templateUrl: './csvexport.component.html',
  styleUrls: ['./csvexport.component.css']
})
export class CsvexportComponent implements OnInit {

  isVisible = false;
  constructor(private notification: NzNotificationService,private service:CsvexportService) { }
  ngOnInit(): void {
    this.findProductList({});
    this.loadingBaseSelectData();
  }

  private findProductList(body){
    this.service.findProductList(body).subscribe(result=>{
      console.log(result);
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

    this.isVisible = true;
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


  isConfirmLoading = false;
  handleCancel(){
    this.isVisible = false;
  }
  handleOk(){
    this.isVisible = false;
  }

  platformId;
  pfaccountNm;
  platformNm = '';
  pfaccountId;
  platIsLoading = false;

  accountList=[];
  platList=[];
  onSearchByPlat(pfnm: string): void {
    this.platIsLoading = true;
    pfnm= pfnm==null?'':pfnm;
    this.platSearchChange$.next(pfnm);
  }
  provinceAllChange(type,id): void {

    if(type == 0){
      if(id == this.platformId){

      }else{
        this.pfaccountNm = '';
        this.getPfaccountInfo(id);
      }
      this.platformId = id;
    }else if(type == 1){
      this.pfaccountId = id;
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

  platSearchChange$ = new BehaviorSubject('');
  private loadingBaseSelectData(){
    console.log(11);
    const getRandomNameList = (pfnm: string) =>
      this.service.getPlatInfo(pfnm).pipe(map((results: any) =>
        results
      )).pipe(
        map((results: any) => {
          return results;
        }));

    const optionList$: Observable<any> =
      this.platSearchChange$.asObservable().pipe(
        debounceTime(500)).pipe(
        switchMap(getRandomNameList));

    optionList$.subscribe(result => {
      this.platIsLoading = false;
      if(result.code == 0){
        this.platList = result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    });
  }
}
