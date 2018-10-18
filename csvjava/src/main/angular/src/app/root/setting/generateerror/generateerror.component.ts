import { Component, OnInit } from '@angular/core';
import {debounceTime, map, switchMap} from "rxjs/internal/operators";
import {BehaviorSubject, Observable} from "rxjs/index";
import {CsvTemplateInfo} from "../../../entity/tempData";
import {PublicService} from "../../../http/public.service";

@Component({
  selector: 'app-generateerror',
  templateUrl: './generateerror.component.html',
  styleUrls: ['./generateerror.component.css']
})
export class GenerateerrorComponent implements OnInit {

  constructor(private publicService:PublicService,private service ) { }

  displayData= [];//画面显示数据集合
  dataSet = [];//查询结果集合

  //查询条件
  condi = {
    websiteOrderNo:""
  };
  websiteOrderNo = '';

  ngOnInit(): void {

  }

  currentPageDataChange($event): void {
    console.log($event)
  }

  selectData(){

    //初始化变量
    this.displayData= [];
    this.dataSet = [];

    this.condi.websiteOrderNo = this.websiteOrderNo;
    this.findGenerateerrorList(this.condi);


  }

  private updDelFlag(csvtempid){
    return this.service.updDelFlag(csvtempid).pipe(map(data=>{
      this.selectData();
      return data
    }));
  }


  delete(data){
    this.updDelFlag(data.genen).subscribe(result=>{
      if(result.code == 0){

      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    });
  }

  private findGenerateerrorList(body){
    this.publicService.sendLoading(true);
    this.service.findGenerateerrorList(body).subscribe(result=>{
      this.publicService.sendLoading(false);
      console.log(result);
      if(result.code == 0){
        this.dataSet = result.data == null?[]:result.data;
        this.displayData = [ ...this.dataSet ];
      }else {
        console.error(result.msg);
      }
    })
  }
}
