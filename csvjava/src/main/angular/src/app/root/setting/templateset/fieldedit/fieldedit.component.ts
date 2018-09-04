import {AfterViewInit, Component, Input, OnInit, Output, ViewChild} from '@angular/core';
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
  getEleData = '_sysCd';

  @Input() fieldList = [];
  @ViewChild("editDiv1") editDiv1;
  selectFielDetail:CsvTemplateDetail = new CsvTemplateDetail();
  selectModelType = 0;
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
  data = [];
  provinceChange(fieldType){

  }


  onChangeSorTable(ev){
    this.fieldList ;
    console.log(ev);
  }

  deleteField(data,i){
    this.fieldList.splice(i,1);
  }

  isConfirmLoading = false;
  isVisible = false;
  handleCancel(): void {
    this.isVisible = false;

    let editDiv = this.editDiv1.nativeElement;
    editDiv.innerHTML = '';
    this.sendFieldListData(this.fieldList);
  }
  handleOk(): void {
    if(!this.util.isEmpty(this.selectFielDetail.fieldKey)){
      this.util.msg.warning('表头不能为空！');
      return
    }

    let editDiv = this.editDiv1.nativeElement;
    let childNodes = editDiv.childNodes;
    for(let ele of childNodes){
      if(ele.className == 'stop-propagation'){
        // let jsonData = JSON.parse((ele.getAttribute(this.getEleData )+'').replace(/'/g,'"'));
      }
    }
    this.selectFielDetail.fieldValue = editDiv.innerHTML+'';
    if(this.selectModelType == 0){
      let tempList = [];
      for(let t of this.fieldList){
        tempList.push(t);
      };
      tempList.push(this.selectFielDetail);
      this.fieldList = tempList;
    }else{

    }
    this.isVisible = false;
    editDiv.innerHTML = '';
   this.sendFieldListData(this.fieldList);
  }
  private sendFieldListData(list){
    this.service.sendFieldListData(list);
  }
  showModel(csvTemplateDetail? :CsvTemplateDetail){
    if(csvTemplateDetail == null){
      this.selectModelType = 0;
      console.info('新增');
      this.selectFielDetail = new CsvTemplateDetail();
    }else{
      this.selectFielDetail = csvTemplateDetail;
      console.info('编辑');
      this.selectModelType = 1;
      let editDiv = this.editDiv1.nativeElement;
      editDiv.innerHTML =csvTemplateDetail.fieldValue;
    }
    this.isVisible = true;

    let classdiv:any =  document.getElementsByClassName("field_input_div")[0];
    this.msgTextLastPos(classdiv);
    this.saveRange();
  }



  lastRange:(any|Range) = {};//光标停留位置




  ngOnInit() {
    this.getSysCodeList(1);
  }



  /*记录光标位置*/
  saveRange(){
    let T_lastRange = this.util.saveRange("stop-propagation");//保存光标位置
    if(T_lastRange!=null){
      this.lastRange = T_lastRange;//保存光标位置
    }
  }

  //把光标移到末尾
  msgTextLastPos(obj) {
    this.util.msgTextLastPos(obj);
  }

  //插入相应文案
  clickFun(item){
    let htmlSrc = '';
    /*let test = `<span class="stop-propagation" contenteditable="false"
    ${ this.getEleData } = "${JSON.stringify(item.sysCd).replace(/"/g,'\'')}"
   (click)="stopEvent($event)">${item.sysNm}</span>`;  */

    let test = `<span class="stop-propagation" contenteditable="false" >${item.sysNm}</span><span editDiv="true"></span>`;
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
    // this.util.insertContent(`<span contenteditable="true"></span>`,this.lastRange);//指定位置插入内容
    let insertSpanList:any = document.getElementsByClassName('stop-propagation');
    for(let span of insertSpanList){
      span.addEventListener('click',function(e){
        e.stopPropagation();//阻止事件广播
      },false);
    }
  }

  private getSysCodeList(typecd){
    this.service.getSysCodeList(typecd).subscribe(result=>{
      if(result.code == 0){
        this.data = result.data == null?[]:result.data;
      }else {

      }
    })
  }


  textValue(formatedStr){
    let map = [{
      key:123,
      value:133
    }];
    for (let k of map) {
      formatedStr = formatedStr.replaceAll("\\$\\{:"+k['key']+"\\}","%`~"+ k['value']+"^%`~");
    }
  }
}
