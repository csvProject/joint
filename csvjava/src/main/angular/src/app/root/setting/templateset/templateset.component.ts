import { Component, OnInit } from '@angular/core';
import {TemplatesetService} from "../../../http/templateset.service";
import {CsvTemplateInfo} from "../../../entity/tempData";
import {BehaviorSubject, Observable} from "rxjs/index";
import {debounceTime, map, switchMap} from "rxjs/internal/operators";

@Component({
  selector: 'app-templateset',
  templateUrl: './templateset.component.html',
  styleUrls: ['./templateset.component.css']
})
export class TemplatesetComponent implements OnInit {
  csvtempNm='';
  platformId = null;
  pfaccountId = null;
  ptypeId = null;
  sId = null;

  constructor(private service:TemplatesetService) { }
  cancel(dataInfo): void {
    this.showModal(0,1,dataInfo);
  }

  confirm(dataInfo): void {
    this.showModal(0,2,dataInfo);
  }

  templateInfo = new CsvTemplateInfo();

  allChecked = false;
  disabledButton = true;
  checkedNumber = 0;
  displayData = [];
  operating = false;
  dataSet = [];
  indeterminate = false;

  platSearchChange$ = new BehaviorSubject('');
  selectedPlat = '';
  selectedAccount = '';
  platList = [];
  accountList = [];
  supplierList = [];
  pTypeList = [];
  platIsLoading = false;
  onSearchByPlat(pfnm: string): void {
    this.platIsLoading = true;
    pfnm= pfnm==null?'':pfnm;
    this.platSearchChange$.next(pfnm);
  }

  ngOnInit(): void {
    let csvTemplateInfo = new CsvTemplateInfo();
    this.getTemplateInfo(csvTemplateInfo);
    // tslint:disable-next-line:no-any
   this.loadingBaseSelectData();
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

  private loadingBaseSelectData(){
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
    this.getSupplierList();
    this.getPtypeList();
  }

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

  operateData(): void {
    this.operating = true;
    setTimeout(_ => {
      this.dataSet.forEach(value => value.checked = false);
      this.refreshStatus();
      this.operating = false;
    }, 1000);
  }


  private getTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    this.service.getTemplateInfo(csvTemplateInfo).subscribe(result=>{
      if(result.code == 0){
        this.dataSet = result.data == null?[]:result.data;
        this.dataSet.forEach(value => value.checked = false);
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }

  private getPtypeList() {
    this.service.getPtypeList().subscribe(result=>{
      if(result.code == 0){
        this.pTypeList = result.data == null?[]:result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }
  private getSupplierList() {
    this.service.getSupplierList().subscribe(result=>{
      if(result.code == 0){
        this.supplierList = result.data == null?[]:result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }


  titleList=['新增模板','编辑模板','编辑字段'];
  modalType;
  isVisible = false;
  isConfirmLoading = false;

  delete(type,data){

  }
  showModal(i,type,dataInfo?): void {
    this.isVisible = true;
    this.modalType = type;
    if(type == 0){
      if(dataInfo == undefined){
        this.templateInfo = new CsvTemplateInfo()
      } else{
        this.templateInfo = Object.create(dataInfo);
        this.templateInfo.csvtempId = null;
      }
    }else if(type == 1){
      this.templateInfo = dataInfo;
    }else if(type == 2){
      // this.templateInfo = csvTemplateInfo;
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
