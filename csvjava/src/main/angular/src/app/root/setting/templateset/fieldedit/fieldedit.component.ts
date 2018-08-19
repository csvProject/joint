import { Component, OnInit } from '@angular/core';
import {CsvTemplateDetail} from "../../../../entity/tempData";
import {CurrencyUtil} from "../../../../util/currencyUtil";

@Component({
  selector: 'app-fieldedit',
  templateUrl: './fieldedit.component.html',
  styleUrls: ['./fieldedit.component.css']
})
export class FieldEditComponent implements OnInit {
  selectFielDetail:CsvTemplateDetail = new CsvTemplateDetail();
  radioValue = 0;
  selectTrueField;
  selectTrueFieldList= [
    {
    pfaccountId:1,
    pfaccountNm:1
  },
    {
      pfaccountId:2,
      pfaccountNm:2
    },
  ];
  data = [
    'Racing car sprays burning fuel into crowd.',
    'Japanese princess to wed commoner.',
    'Australian walks 100km after outback crash.',
    'Man charged over missing wedding girl.',
    'Los Angeles battles huge wildfires.'
  ];
  provinceChange(fieldType){

  }

  itemObjectsLeft: any[] = [
    { id: 1, name: 'Windstorm' },
    { id: 2, name: 'Bombasto' },
    { id: 3, name: 'Magneta' }
  ];

  onChangeSorTable(ev){
    console.log(ev);
  }

  deleteField(data){

  }

  isConfirmLoading = false;
  isVisible = false;
  handleCancel(): void {
    this.isVisible = false;

  }
  handleOk(): void {

  }
  showModel(csvTemplateDetail :CsvTemplateDetail){
    console.log(csvTemplateDetail);
    this.isVisible = true;
  }


  lastRange:(any|Range) = {};//光标停留位置

  constructor(private util:CurrencyUtil){}


  ngOnInit() {
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
