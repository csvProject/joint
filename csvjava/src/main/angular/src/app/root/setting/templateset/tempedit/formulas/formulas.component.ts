import {Component, Input, OnInit} from '@angular/core';
import {CsvCustomField} from "../../../../../entity/tempData";
import {CurrencyUtil} from "../../../../../util/currencyUtil";

@Component({
  selector: 'app-formulas',
  templateUrl: './formulas.component.html',
  styleUrls: ['./formulas.component.css']
})
export class FormulasComponent implements OnInit {
  @Input() csvCustomFields :CsvCustomField[];

  constructor(private util:CurrencyUtil) { }

  ngOnInit() {
  }

  addData(){
    if(this.csvCustomFields ==null){
      this.csvCustomFields = [];
    }else{
      if(this.csvCustomFields.length == 0){
        this.csvCustomFields.push(new CsvCustomField());
      }else{
        if(this.util.isEmpty(this.csvCustomFields[this.csvCustomFields.length-1].cfieldNm)){
          this.csvCustomFields.push(new CsvCustomField());
        }else{
          return;
        }
      }
    }
  }
  deleteData(i,data){
    this.csvCustomFields.splice(i,1);
  }
}
