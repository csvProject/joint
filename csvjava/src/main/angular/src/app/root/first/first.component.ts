import { Component, OnInit } from '@angular/core';
import {CurrencyUtil} from "../../util/currencyUtil";


@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {
  lastRange:(any|Range) = {};//光标停留位置

  constructor(private util:CurrencyUtil){}

  csvTableHead = ['seq','name','sex','age'];
  csvTableData = [
    [1,'wkm','男',22],
    [1,'wkm','男',22]
  ];


  ngOnInit() {
  }

  //文件下载
  fileDown(){

  }

  fileUp(ev){
    let file= ev.target.files[0];
    ev.target.value = '';

    this.util.readerFile(file).then((result)=>{
      console.log(result);
      console.log(this.util.csvData(result));
    })
  }

  /*记录光标位置*/
  saveRange(){
    this.lastRange = this.util.saveRange();//保存光标位置
  }

  //插入相应文案
  clickFun(htmlSrc){
    let test = `<span class="stop-propagation" style="color: red;" contenteditable="false" 
    (click)="stopEvent($event)">123</span>`;
    htmlSrc = test;

    if(this.lastRange.startContainer==undefined ){
      return ;
    }else{
      if(this.lastRange.startContainer.parentNode.getAttribute("editDiv") || this.lastRange.startContainer.getAttribute("editDiv")) {

      }else{
        return;
      }
    }
    this.util.insertContent(htmlSrc,this.lastRange);//指定位置插入内容
    let insertSpanList:any = document.getElementsByClassName('stop-propagation');
    for(let span of insertSpanList){
      span.addEventListener('click',function(e){
       e.stopPropagation();//阻止事件广播
      },false);
    }
  }

}
