import { Component, OnInit } from '@angular/core';
import {CurrencyUtil} from "../../util/currencyUtil";

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {
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
}
