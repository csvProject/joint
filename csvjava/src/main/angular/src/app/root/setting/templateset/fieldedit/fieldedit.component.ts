import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {CsvTemplateDetail} from "../../../../entity/tempData";
import {CurrencyUtil} from "../../../../util/currencyUtil";
import {TemplatesetService} from "../../../../http/templateset.service";

@Component({
  selector: 'app-fieldedit',
  templateUrl: './fieldedit.component.html',
  styleUrls: ['./fieldedit.component.css']
})
export class FieldEditComponent implements OnInit {
  constructor(private util:CurrencyUtil,private service:TemplatesetService){}
  @Input() fieldList = [];
  @ViewChild("editDiv1") editDiv1;
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
    {
      key:1,
      value:'商品名1'
    },
    {
      key:2,
      value:'商品名2'
    },
    {
      key:3,
      value:'商品名3'
    },
    {
      key:4,
      value:'商品名4'
    },
    {
      key:5,
      value:'商品名5'
    }
  ];
  provinceChange(fieldType){

  }


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

    let childNodes = this.editDiv1.nativeElement.childNodes;
    for(let ele of childNodes){
      if(ele.className == 'stop-propagation'){
        let jsonData = JSON.parse((ele.getAttribute('jsonData')+'').replace(/'/g,'"'));
      }
    }
    console.log(this.editDiv1);
  }
  showModel(csvTemplateDetail? :CsvTemplateDetail){
    console.log(csvTemplateDetail.csvtempId);

    this.isVisible = true;


  }



  lastRange:(any|Range) = {};//光标停留位置




  ngOnInit() {
  }


  /*记录光标位置*/
  saveRange(){
    this.lastRange = this.util.saveRange();//保存光标位置
  }

  //插入相应文案
  clickFun(item){
    let htmlSrc = '';
    let test = `<span class="stop-propagation" 
    style="color: red;" contenteditable="false" 
    jsonData = "${JSON.stringify(item).replace(/"/g,'\'')}"
    (click)="stopEvent($event)">${item.value}</span>`;
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


  textValue(formatedStr){
    let map = [{
      key:123,
      value:133
    }]
    for (let k of map) {
      formatedStr = formatedStr.replaceAll("\\$\\{:"+k['key']+"\\}","%`~"+ k['value']+"^%`~");
    }
  }
}
