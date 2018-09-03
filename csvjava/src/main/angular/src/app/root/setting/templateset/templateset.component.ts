import { Component, OnInit } from '@angular/core';
import { TemplatesetService } from "../../../http/templateset.service";
import {CsvTempBat, CsvTemplateInfo} from "../../../entity/tempData";
import { BehaviorSubject, Observable } from "rxjs/index";
import { debounceTime, map, switchMap } from "rxjs/internal/operators";
import {CurrencyUtil} from "../../../util/currencyUtil";

@Component({
  selector: 'app-templateset',
  templateUrl: './templateset.component.html',
  styleUrls: ['./templateset.component.css']
})
export class TemplatesetComponent implements OnInit {
  csvtempNm='';

  constructor(private service:TemplatesetService,private util:CurrencyUtil) { }
  cancel(dataInfo): void {
    this.showModal(0,1,dataInfo);
  }

  confirm(dataInfo): void {
    this.showModal(0,2,dataInfo);
  }

  templateInfo = new CsvTemplateInfo();
  selectTemplateInfo = new CsvTemplateInfo();

  operating = false;
  dataSet = [];

  platSearchChange$ = new BehaviorSubject('');
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
    this.getTemplateInfo(this.selectTemplateInfo);
   this.loadingBaseSelectData();
   this.service.getFieldListData().subscribe(list=>{
     this.fieldList = list;
   })
  }

  provinceAllChange(type,id): void {

    if(type == 0){
      if(id == this.selectTemplateInfo.platformId){

      }else{
        this.selectTemplateInfo.pfaccountNm = '';
        this.getPfaccountInfo(id);
      }
      this.selectTemplateInfo.platformId = id;
    }else if(type == 1){
      this.selectTemplateInfo.pfaccountId = id;
    }else if(type == 2){
      this.selectTemplateInfo.ptypeId = id;
    }else if(type == 3){
      this.selectTemplateInfo.sId = id;
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
    console.log($event)
  }



  operateData(selectTemplateInfo): void {
    this.operating = true;
    this.getTemplateInfo(selectTemplateInfo);
  }


  private getTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    this.service.getTemplateInfo(csvTemplateInfo).subscribe(result=>{
      this.operating?this.operating = false:null;
      if(result.code == 0){
        this.dataSet = result.data == null?[]:result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }

  private insertTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    return this.service.insertTemplateInfo(csvTemplateInfo).pipe(map(data=>{
      this.getTemplateInfo(this.selectTemplateInfo);
      return data
    }));
  }

  private updateTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    return this.service.updateTemplateInfo(csvTemplateInfo).pipe(map(data=>{
      this.getTemplateInfo(this.selectTemplateInfo);
      return data
    }));
  }
  private fieldUpdate(csvTempBat:CsvTempBat){
    return this.service.fieldUpdate(csvTempBat).pipe(map(data=>{
      return data
    }));
  }
  private deletePlatInfo(csvtempid){
    return this.service.deletePlatInfo(csvtempid).pipe(map(data=>{
      this.getTemplateInfo(this.selectTemplateInfo);
      return data
    }));
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
    this.deletePlatInfo(data.csvtempId).subscribe(result=>{
      if(result.code == 0){

      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    });
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
      console.log(dataInfo);
      this.templateInfo = dataInfo;
      this.getFieldListCsvtempid(dataInfo.csvtempId);
    }
  }

  fieldList:any = [];
  private getFieldListCsvtempid(csvtempId){
    console.log(csvtempId);
    this.service.getFieldListCsvtempid(csvtempId).subscribe(result=>{
      if(result.code == 0){
        this.fieldList = result.data == null?[]:result.data;
      }else if(result.code == 1){

      }else {

      }
    })
  }

  handleOk(modalType,templateInfo): void {
    this.isConfirmLoading = true;
    if(modalType == 1|| modalType == 0){
      if(!this.checkTemplateInfo(templateInfo)){
        return;
      }
      if(modalType == 0){
        this.insertTemplateInfo(templateInfo) .subscribe(result=>{
          if(result.code == 0){
            this.isVisible = false;
            this.isConfirmLoading = false;
          }else if(result.code == 1){

          }else{
            console.error(result.msg);
          }
        });
      }else{
        this.updateTemplateInfo(templateInfo) .subscribe(result=>{
          if(result.code == 0){
            this.isVisible = false;
            this.isConfirmLoading = false;
          }else if(result.code == 1){

          }else{
            console.error(result.msg);
          }
        });
      }
    }else if(modalType == 2){
      let csvTempBat = new CsvTempBat();
      for (let i = 0;i<this.fieldList.length;i++){
       this.fieldList[i].fieldSort = i;
      }
      csvTempBat.csvtempId = templateInfo.csvtempId;
      csvTempBat.csvTemplateDetailDtoList = this.fieldList;
        this.fieldUpdate(csvTempBat).subscribe(result=>{
          if(result.code == 0){
            this.isVisible = false;
            this.isConfirmLoading = false;
          }else if(result.code == 1){

          }else{
            console.error(result.msg);
          }
        });
    }



  }
  checkTemplateInfo(templateInfo:CsvTemplateInfo):boolean{
    let num = 0;
    if(!this.util.isEmpty(templateInfo.csvtempNm)){
      num++;
      this.util.msg.warning("请填写模板名称");
    }
    if(!this.util.isEmpty(templateInfo.platformId)){
      num++;
      this.util.msg.warning("请选择平台");
    }
    if(!this.util.isEmpty(templateInfo.pfaccountId)){
      num++;
      this.util.msg.warning("请选择账号");
    }
    if(!this.util.isEmpty(templateInfo.ptypeId)){
      num++;
      this.util.msg.warning("请选择产品分类");
    }
    if(!this.util.isEmpty(templateInfo.sId)){
      num++;
      this.util.msg.warning("请选择供应商");
    }

    return num == 0?true:false;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.isConfirmLoading = false;
    this.getTemplateInfo(this.selectTemplateInfo);
  }
}
