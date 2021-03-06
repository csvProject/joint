import {Component, DoCheck, OnInit} from '@angular/core';
import { TemplatesetService } from "../../../service/templateset.service";
import {CsvCustomField, CsvTempBat, CsvTemplateInfo} from "../../../entity/tempData";
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
  //取出后的原始数据
  dataSet = [];
  //画面显示用集合（筛选或排序后）
  displayData = [];

  platSearchChange$ = new BehaviorSubject('');
  //平台集合
  platList = [];
  //平台账号集合
  accountList = [];
  //供应商集合
  supplierList = [];
  //产品类型集合
  pTypeList = [];
  //下拉框自查询时加载状态
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
        this.displayData = [ ...this.dataSet ];
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

  private copyTemplateInfo(csvTemplateInfo:CsvTemplateInfo){
    return this.service.copyTemplateInfo(csvTemplateInfo).pipe(map(data=>{
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


  titleList=['新增模板','编辑模板','编辑字段','复制模板'];
  modalType;//打开画面类型（'新增模板','编辑模板','编辑字段','复制模板'）
  isVisible = false;

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
    if(type == 0){//新增
      if(dataInfo == undefined){
        this.templateInfo = new CsvTemplateInfo()
      } else{
        this.templateInfo = Object.assign({},dataInfo);
        this.templateInfo.csvtempId = null;
      }
      console.log("sendTemplateInfoData");
      this.templateInfo.modalType = type;
      this.service.sendTemplateInfoData(this.templateInfo);
    }else if(type == 1){//编辑基本信息
      this.templateInfo = Object.assign({},dataInfo);
      console.log("sendTemplateInfoData");
      this.templateInfo.modalType = type;
      this.service.sendTemplateInfoData(this.templateInfo);

    }else if(type == 2){//编辑csv字段画面
      this.templateInfo = Object.assign({},dataInfo);
      this.getFieldListCsvtempid(this.templateInfo.csvtempId);
      this.templateInfo.modalType = type;
      this.service.sendTemplateInfoDataForField(this.templateInfo);

    }else if(type == 3){//复制
      this.templateInfo = Object.assign({},dataInfo);
      this.getFieldListCsvtempid(this.templateInfo.csvtempId);
      this.templateInfo.modalType = type;
      this.service.sendTemplateInfoData(this.templateInfo);
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
    if(modalType == 1|| modalType == 0){
      if(modalType == 0){
        if(!this.checkTemplateInfo(templateInfo)){
          return;
        }
        this.checkCsvCustomField(templateInfo);
        if(this.findCopyEle(templateInfo)){
          return
        }
        this.insertTemplateInfo(templateInfo) .subscribe(result=>{
          if(result.code == 0){
            this.isVisible = false;
          }else if(result.code == 1){

          }else{
            console.error(result.msg);
          }
        });
      }else{
        this.checkCsvCustomField(templateInfo);
        if(this.findCopyEle(templateInfo)){
          return
        }
        this.updateTemplateInfo(templateInfo) .subscribe(result=>{
          if(result.code == 0){
            this.isVisible = false;
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
          }else if(result.code == 1){

          }else{
            console.error(result.msg);
          }
        });
    }else if (modalType == 3){
      if(!this.checkTemplateInfo(templateInfo)){
        return;
      }
      this.checkCsvCustomField(templateInfo);
      if(this.findCopyEle(templateInfo)){
        return
      }
      templateInfo.csvTemplateDetailDtoList = this.fieldList;
      this.copyTemplateInfo(templateInfo) .subscribe(result=>{
        if(result.code == 0){
          this.isVisible = false;
        }else if(result.code == 1){

        }else{
          console.error(result.msg);
        }
      });
    }
  }

  private checkCsvCustomField(templateInfo){
    if(templateInfo.csvCustomFieldDtoList ==null){
      templateInfo.csvCustomFieldDtoList = [];
      return;
    }else{
      if(templateInfo.csvCustomFieldDtoList.length == 0){
        templateInfo.csvCustomFieldDtoList = [];
      }else{
        let len = templateInfo.csvCustomFieldDtoList.length;
        if(!this.util.isEmpty(templateInfo.csvCustomFieldDtoList[len-1].cfieldNm)){
          templateInfo.csvCustomFieldDtoList.splice(len-1,1);
        }else{
          return;
        }
      }
    }

  }
  findCopyEle(templateInfo){
    if(templateInfo.csvCustomFieldDtoList ==null){
      templateInfo.csvCustomFieldDtoList = [];
      return false;
    }else {
      let cfieldNms = [];
      templateInfo.csvCustomFieldDtoList.forEach((v,i)=>{
        cfieldNms.push(v.cfieldNm);
      });
      let src = this.util.findCopyEle(cfieldNms);
      if(src == null){
        return false
      }else{
        this.util.msg.warning("重复公式名:"+src);
        return true;
      }
    }
  }

  private checkTemplateInfo(templateInfo:CsvTemplateInfo):boolean{
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
    this.getTemplateInfo(this.selectTemplateInfo);
  }

  //排序筛选处理-----------------------
  sortName = null;//排序用变量
  sortValue = null;//排序用变量
  //表头排序
  sort(sort: { key: string, value: string }): void {
    this.sortName = sort.key;
    this.sortValue = sort.value;
    this.search();
  }

  //排序后筛选
  search(): void {
    /** filter data **/
    const filterFunc = item => (true);
    const data = this.dataSet.filter(item => filterFunc(item));
    /** sort data **/
    if (this.sortName && this.sortValue) {
      this.displayData = data.sort((a, b) =>
        (this.sortValue === 'ascend') ? (a[ this.sortName ] > b[ this.sortName ] ? 1 : -1) : (b[ this.sortName ] > a[ this.sortName ] ? 1 : -1));
    } else {
      this.displayData = data;
    }
  }
  //排序筛选处理-end------------

}
