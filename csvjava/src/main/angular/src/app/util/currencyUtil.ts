import {CsvData} from "../entity/CsvData";

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

}
