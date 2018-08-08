import {CsvData} from "../entity/CsvData";
const documentSys:(any|Document) = document;

export class CurrencyUtil {

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
}
