import { Component, OnInit, TemplateRef } from '@angular/core';
import { NzNotificationService} from 'ng-zorro-antd';
import { NzNotificationDataOptions } from 'ng-zorro-antd/src/notification/nz-notification.definitions';
import { CsvexportService } from '../../http/csvexport.service';
import { debounceTime, map, switchMap } from 'rxjs/internal/operators';
import { BehaviorSubject, Observable } from 'rxjs/index';
import { CurrencyUtil } from '../../util/currencyUtil';
import { PublicService } from '../../http/public.service';

@Component({
  selector: 'app-csvexport',
  templateUrl: './csvexport.component.html',
  styleUrls: ['./csvexport.component.css']
})
export class CsvexportComponent implements OnInit {

  isVisible = false;
  constructor(private notification: NzNotificationService,private service:CsvexportService,private util:CurrencyUtil,private fileService:PublicService) { }
  private get msg(){
    return this.util.msg;
  }

  //保存选中记录
  productListSel = [];

  //页面page设定
  pageset={pageSize:10,pageIndex:1,count:1};

  //查询条件
  condi = {
    sjStartDt:"",
    sjEndDt:"",
    sku:"",
    ptypeId:"0",
    sId:"0",
    pageSize:this.pageset.pageSize,
    pageStart:1
  };

  loading = true;

  ngOnInit(): void {
    this.selectData();
    this.loadingBaseSelectData();

    this.getSupplierList();
    this.getPtypeList();
  }

  private findProductList(body,doit){
    this.loading = true;
    this.service.findProductList(body).subscribe(result=>{
      this.loading = false;
      console.log(result);
      if(result.code == 0){
        this.pageset.count = result.count;
        this.dataSet = result.data==null?[]:result.data;
        //this.dataSet.forEach(data => data.checked = false)

        //取得数据与选中集合比对，有则checkbox勾选
        for(let i = 0,len=this.dataSet.length; i < len; i++) {
          this.dataSet[i].checked = false;
          for(let j = 0,len=this.productListSel.length; j < len; j++) {
            if (this.dataSet[i].productId == this.productListSel[j].productId){
              this.dataSet[i].checked = true;
              break;
            }
          }
        }

        doit();

      }else {
        console.error(result.msg);
      }
    })
  }
  allChecked = false;
  disabledButton = true;
  checkedNumber = 0;
  displayData= [];
  dataSet = [];
  indeterminate = false;

  currentPageDataChange($event): void {
    this.displayData = $event;
  }

  refreshStatus(): void {
    const allChecked = this.dataSet.every(value => value.checked === true);
    const allUnChecked = this.dataSet.every(value => !value.checked);
    //all checkbox控制
    this.allChecked = allChecked;
    this.indeterminate = (!allChecked) && (!allUnChecked);
    //this.disabledButton = !this.dataSet.some(value => value.checked);
    //this.checkedNumber = this.dataSet.filter(value => value.checked).length;

    //遍历获取选中记录放入选中集合中,没选中的从选中集合中去除
    for(let i = 0,len=this.displayData.length; i < len; i++) {
      let hav = false;

      if (this.displayData[i].checked){
        //如果是选中的
        for(let j = 0,len=this.productListSel.length; j < len; j++) {
          if (this.displayData[i].productId == this.productListSel[j].productId){
            hav = true;
            break;
          }
        }

        if (!hav){
          this.productListSel.push(Object.assign({}, this.displayData[i]));
        }
      }else{
        //如果是未选中的
        for(let j = 0,len=this.productListSel.length; j < len; j++) {
          if (this.displayData[i].productId == this.productListSel[j].productId){
            this.productListSel.splice(j,1);
            break;
          }
        }
      }
    }

    //导出按钮显隐控制
    this.disabledButton = this.productListSel.length ==0 ? true:false;
    //导出选中件数
    this.checkedNumber = this.productListSel.length;
  }

  checkAll(value: boolean): void {
    this.displayData.forEach(data => data.checked = value);
    this.refreshStatus();
  }

  operateData(): void {
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
    let option:NzNotificationDataOptions = { nzDuration:0,nzStyle: {
        width : '920px',
      },
      nzClass: 'test-class' };
    this.notification.template(template,option);
  }


  isConfirmLoading = false;
  handleCancel(){
    this.isVisible = false;
  }
  handleOk(template){
    let body = {
      platformId:this.platformId,//平台ID
      pfaccountId:this.pfaccountId,//平台账号ID
      //productDtoList:this.dataSet.filter(value => value.checked)
      productDtoList:this.productListSel

    };
    if(this.checkTemplateInfo(body)){
      this.exportCSV(body,template);
      this.isVisible = false;
    }
  }
  private checkTemplateInfo(body):boolean{
    let num = 0;
    if(!this.util.isEmpty(body.platformId)){
      num++;
      this.util.msg.warning('请选择平台');
    }
    if(!this.util.isEmpty(body.pfaccountId)){
      num++;
      this.util.msg.warning('请选择账号');
    }
    return num == 0?true:false;
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

  skuSrc = '';
  sNm='';
  ptypeNm='';

  ptypeId='';
  sId='';

  sjDatetime = [];
  sjStartDt;
  sjEndDt;


  pTypeList = [];
  supplierList = [];
  selectAllChange(type,id){
    if(type == 0){
      this.ptypeId = id
    }else {
      this.sId = id
    }
  }
  onChangeDate(ev){
    this.sjStartDt = this.util.dateFormat(ev[0],'yyyy-MM-dd');
    this.sjEndDt = this.util.dateFormat(ev[1],'yyyy-MM-dd');
  }

  selectData(){

    //初始化变量
    this.allChecked = false;
    this.disabledButton = true;
    this.checkedNumber = 0;
    this.displayData= [];
    this.dataSet = [];
    this.indeterminate = false;
    this.productListSel = [];

    this.pageset.pageIndex = 1;

    this.condi.sjStartDt = this.sjStartDt;
    this.condi.sjEndDt = this.sjEndDt;
    this.condi.sku = this.skuSrc;
    this.condi.ptypeId = this.ptypeId;
    this.condi.sId = this.sId;
    this.condi.pageStart = 1;
    this.findProductList(this.condi,function(){ return });


  }

  pageIndexChange(){
    this.condi.pageStart = this.pageset.pageIndex;
    this.findProductList(this.condi,()=>{this.refreshStatus()});


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

  private getPfaccountInfo(pfid): void {
    this.service.getPfaccountInfo(pfid).subscribe(result=>{
      if(result.code == 0){
        this.accountList = result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }

  zipFileName = ''; //下载文件名
  noCsvTempList:any = []; //无模板商品信息
  private exportCSV(body,template){
    this.service.exportCSV(body).subscribe(result=>{
      if(result.code == 0){
        this.zipFileName = result.data.zipFileName+'.zip';
        this.noCsvTempList = result.data.noCsvTempList == null?[]:result.data.noCsvTempList;
        this.fileService.downloadFile(this.zipFileName);
        if(this.noCsvTempList.length !=  0){
          this.createBasicNotification(template);
        }
        //this.dataSet.forEach(value => value.checked = false);
        //this.refreshStatus();
      }else {
        this.msg.error(result.msg);
      }
    })
  }

  platSearchChange$ = new BehaviorSubject('');
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
  }
}
