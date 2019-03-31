import { Component, OnInit } from '@angular/core';
import {debounceTime, map, switchMap} from "rxjs/internal/operators";
import {BehaviorSubject, Observable} from "rxjs/index";
import {PublicService} from "../../../service/public.service";
import {GenerateerrorService} from "../../../service/generateerror.service";

@Component({
  selector: 'app-generateerror',
  templateUrl: './generateerror.component.html',
  styleUrls: ['./generateerror.component.css']
})
export class GenerateerrorComponent implements OnInit {

  constructor(private publicService:PublicService,private service : GenerateerrorService ) { }

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
    this.findErrOrderNo(this.condi);


  }

  private updDelFlag(generateErrorId){
    return this.service.updDelFlag(generateErrorId).pipe(map(data=>{
      this.selectData();
      return data
    }));
  }


  delete(data){
    this.updDelFlag(data.generateErrorId).subscribe(result=>{
      if(result.code == 0){

      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    });
  }

  private findErrOrderNo(body){
    this.publicService.sendLoading(true);
    this.service.findErrOrderNo(body).subscribe(result=>{
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
