import {CsvData} from "../entity/CsvData";
import {Injectable, Injector} from "@angular/core";
import {NzMessageService} from "ng-zorro-antd";
import {InjectorService} from "../http/injector.service";
const documentSys:(any|Document) = document;

@Injectable()
export class CurrencyUtil {
  injector:Injector;
  constructor(private InjectorService:InjectorService){
    this.injector = this.InjectorService.getInjector();
  }
  get msg(): NzMessageService {
    return this.injector.get(NzMessageService);
  }

  getCsvData(list):CsvData{
    let ret:CsvData = null;
    if (list == null || list.length <= 0)return null;
    ret.head = list[0];
    ret.body = [];
    for(let i = 0;i<list.length;i++){
      ret.body.push(list[i]);
    }
    return ret;
  }

  csvExport(data,fileName?){
    if(data == null)return;
    if(fileName == null || fileName == ''){
      fileName = new Date().getDate()+'.csv';
    }
  }

  csvData(result){
    let data = [];
    let regp = new RegExp(".*,\".*,.*\"$");
    let relArr = result.split("\r\n");
    if(relArr != null && relArr.length > 1) {
      for(let key = 0, len = relArr.length; key < len; key++) {
        let values = relArr[key];
        if(regp.test(values)) {
          alert("文件内容中有英文逗号，麻烦修改后再上传，含有英文逗号的内容是：" + values);
          return;
        }
        if(values!=null) {
          let objArr = values.split(",");
          data.push(objArr);
        }
      }
      return data;
    }else{
      return result;
    }
  }

  readerFile(file:File){
    if( typeof(FileReader) !== 'undefined' ){    //H5
      return new Promise<any>(function(resolve, reject) {
        let reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function() {
          resolve(this.result)
        }
      })
    }else{
      alert("IE9及以下浏览器不支持，请使用Chrome或Firefox浏览器");
      return null
    }

  }


  //光标定位到最后
  keepLastIndex(ele) {
    if(ele == null){
      throw new Error("光标对象为空 标签对象 Ele:"+ele);
    }
    if (window.getSelection) {//ie11 10 9 ff safari
      ele.focus(); //解决ff不获取焦点无法定位问题
      let range = window.getSelection();//创建range
      range.selectAllChildren(ele);//range 选择obj下所有子内容
      range.collapseToEnd();//光标移至最后
    }
    else if (documentSys.selection) {//ie10 9 8 7 6 5
      let range:(any|Range) = documentSys.selection.createRange();//创建选择对象
      //let range = document.body.createTextRange();
      range.moveToElementText(ele);//range定位到obj
      range.collapse(false);//光标移至最后
      range.select();
    }
  }

  //插入内容
  insertContent(str,lastRange) {
    if(lastRange == null){
      throw new Error("光标对象为空 lastRange:"+lastRange);
    }
    let selection, range:(Range|any) = lastRange;
    if (!window.getSelection) {
      range.pasteHTML(str);
      range.collapse(false);
      range.select();
    } else {
      selection = window.getSelection ? window.getSelection() : documentSys.selection;
      range.collapse(false);
      let hasR = range.createContextualFragment(str);
      let nullSpan = range.createContextualFragment(`<span></span>`);
      let hasR_lastChild = hasR.lastChild;
      let nullSpan_lastChild = nullSpan.lastChild;
      while (hasR_lastChild && hasR_lastChild.nodeName.toLowerCase() == "br" && hasR_lastChild.previousSibling && hasR_lastChild.previousSibling.nodeName.toLowerCase() == "br") {
        let e = hasR_lastChild;
        hasR_lastChild = hasR_lastChild.previousSibling;
        hasR.removeChild(e);
      }
      while (nullSpan_lastChild && nullSpan_lastChild.nodeName.toLowerCase() == "br" && nullSpan_lastChild.previousSibling && nullSpan_lastChild.previousSibling.nodeName.toLowerCase() == "br") {
        let e = nullSpan_lastChild;
        nullSpan_lastChild = nullSpan_lastChild.previousSibling;
        nullSpan.removeChild(e);
      }
      range.insertNode(hasR);
      range.insertNode(nullSpan);
      if (hasR_lastChild) {
        range.setEndAfter(hasR_lastChild);
        range.setStartAfter(hasR_lastChild);
      }
    }

  };

  //保存光标位置
  saveRange() {
    let selection = window.getSelection ? window.getSelection() : documentSys.selection;
    if (!selection.rangeCount) return;
    let range = selection.createRange ? selection.createRange() : selection.getRangeAt(0);
    return range;
  }


  //时间格式化方法
  //  let retTime = this.mms.dateFormat(new Date(),'yyyy-MM-dd');  // '2017-01-11'
  dateFormat(inData?: Date, format?: string) {
    if (inData == undefined || (inData + '') == '' || (inData + '') == 'null') {
      return null;
    }
    let o = {
      'M+': inData.getMonth() + 1,                 //月份
      'd+': inData.getDate(),                    //日
      'h+': inData.getHours(),                   //小时
      'm+': inData.getMinutes(),                 //分
      's+': inData.getSeconds(),                 //秒
      'q+': Math.floor((inData.getMonth() + 3) / 3), //季度
      'S': inData.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (inData.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
      }
    }
    return format;
  }

  //时间处理201801  -->  2018-01      20180102  -->  2018-01-02    splitSrc : - 或者 / 分割符
  timeToTimeSrc(time, splitSrc?) {
    if (splitSrc == undefined) {
      splitSrc = '-';
    }
    let y = (time + '').substr(0, 2);
    let timeTemp = (time + '').substr(2);
    let timeList = timeTemp.split('');
    let len = timeList.length;
    if (len == 0) {
      return '';
    }
    let timeSrc = '';
    for (let i = 0; i < len; i++) {
      if ((i + 1) % 2 == 0) {
        if (i + 1 != len) {
          timeSrc = timeSrc + timeList[i] + splitSrc;
        } else {
          timeSrc = timeSrc + timeList[i];
        }
      } else {
        timeSrc = timeSrc + timeList[i];
      }
    }
    timeSrc = y + timeSrc;
    return timeSrc;
  }

  isEmpty(s):boolean{
    if( s == undefined || s == null||(s+'').trim() == ''|| (s+'').trim() == '{}'){
      return false;
    }
    return true
  }

  fieldBeStripped():string{
  let ret = '';

  return ret;
  }

  //对象copy
  copyObj(srcObj):any {
    let ret = {};
    for ( let param in srcObj)
    {
      //属性名称
      ret[param] = srcObj[param];

    }

    return ret;
  }
}
