import {AfterViewInit, Component, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CsvCustomField, CsvTemplateDetail} from "../../../../entity/tempData";
import {CurrencyUtil} from "../../../../util/currencyUtil";
import {TemplatesetService} from "../../../../http/templateset.service";
import {Subject} from "rxjs/index";
import {UploadFile} from "ng-zorro-antd";

@Component({
  selector: 'app-fieldedit',
  templateUrl: './fieldedit.component.html',
  styleUrls: ['./fieldedit.component.css']
})
export class FieldEditComponent implements OnInit {
  constructor(private util:CurrencyUtil,private service:TemplatesetService){}


  @Input() fieldList = [];

  @Input() csvtempId;
  @ViewChild("editDiv1") editDiv1;
  selectFielDetail:CsvTemplateDetail = new CsvTemplateDetail();
  selectModelType = 0;
  data = [];
  provinceChange(fieldType){

  }


  onChangeSorTable(ev){
  }

  deleteField(data,i){
    let arr = [];
    this.fieldList.forEach((v,i)=>{
      let a = Object.assign({},v);
      arr.push(a)
    });
    arr.splice(i,1);
    console.log(arr);
    this.fieldList = arr;
  }
  objToArr(arr:any[]){
    return new Array(arr);
  }

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
      this.selectModelType = 1;
      let editDiv = this.editDiv1.nativeElement;
      editDiv.innerHTML =csvTemplateDetail.fieldValue;
    }
    this.isVisible = true;
    this.getCsvCustomField(this.csvtempId);
    let classdiv:any =  document.getElementsByClassName("field_input_div")[0];
    this.msgTextLastPos(classdiv);
    this.saveRange();
  }



  lastRange:(any|Range) = {};//光标停留位置


  copyList = [];
  selectSrc = "";
  selectSrcFun(src){
    let t = [];
    if(src == null){
      src = "";
    }
    for(let l of this.copyList){
      if((l.sysNm+"").indexOf(src)>=0){
        t.push(Object.assign({},l))
      }
    }
    this.data = t;
  }

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
  clickFun(item,type){
    let htmlSrc = '';
    /*let test = `<span class="stop-propagation" contenteditable="false"
    ${ this.getEleData } = "${JSON.stringify(item.sysCd).replace(/"/g,'\'')}"
   (click)="stopEvent($event)">${item.sysNm}</span>`;  */
    if(type == 0){
      htmlSrc = `<span class="stop-propagation" contenteditable="false" inval="`+item.sysCd+`">${item.sysNm}</span><span editDiv="true"></span>`;
    }else if(type == 1){
      htmlSrc = `<span class="stop-propagation" contenteditable="false" inval="`+"t_csvcustom_field."+item.csvCustomFieldId+"t_csvcustom_field"+`">${item.cfieldNm}</span><span editDiv="true"></span>`;
    }
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
        for(let t of this.data){
          let o = Object.assign({},t);
          this.copyList.push(o);
        }
      }else {

      }
    })
  }
  csvCustomFields : CsvCustomField[] = [];
  private getCsvCustomField(csvtempid){
    this.service.getCsvCustomField(csvtempid).subscribe(result=>{
      if(result.code == 0){
        this.csvCustomFields = result.data == null?[]:result.data;
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

  handleChange= (file): boolean => {
      this.util.readerFile(file).catch(ret=>{
        console.error(ret)
      }).then(text=>{
        console.log(text);
      });
    return false;
}
 /* handleChange(info: any): void {

    if (info.fileList.length > 0){
      this.util.readerFile(info.file.originFileObj).catch(ret=>{
        console.error(ret)
      }).then(text=>{
        console.log(text);
      });
    }
  /!*  let subject = new Subject<string>();
    if (info.fileList.length > 0){
      let readerFile = new FileReader();
      readerFile.readAsText(info.file,'gb2312');
      readerFile.onload =(e => {
        let target :any = info.target;
        let resultList = target.result;
        subject.next(resultList)
      })
    }
    subject.asObservable().subscribe(resultList => {
      this.expToFieldList(resultList);//接收读取文件结束后内容
      info.target.value = '' //清除文件内容
    })*!/
  }*/

  private expToFieldList(src:string ){

  }
}
