import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {CsvCustomField} from "../../../../entity/tempData";
import {CurrencyUtil} from "../../../../util/currencyUtil";
import {TemplatesetService} from "../../../../service/templateset.service";
import {NzNotificationDataOptions} from "ng-zorro-antd/src/notification/nz-notification.definitions";
import {NzNotificationService} from "ng-zorro-antd";

@Component({
  selector: 'app-formulas',
  templateUrl: './formulas.component.html',
  styleUrls: ['./formulas.component.css']
})
export class FormulasComponent implements OnInit {
  @Input() csvCustomFields :CsvCustomField[];
  @Input() modalType ;
  massageList = []; //消息弹出集合

  constructor(private notification: NzNotificationService,private util:CurrencyUtil,private service:TemplatesetService) { }

  ngOnInit() {
  }

  addData(){
    if(this.csvCustomFields ==null){
      this.csvCustomFields = [];
    }else{
      let tmp = new CsvCustomField();
      tmp.isNew = true;
      this.csvCustomFields.push(tmp);
      /*if(this.csvCustomFields.length == 0){
        let tmp = new CsvCustomField();
        tmp.isNew = true;
        this.csvCustomFields.push(tmp);
      }else{
        if(this.util.isEmpty(this.csvCustomFields[this.csvCustomFields.length-1].cfieldNm)){
          let tmp = new CsvCustomField();
          tmp.isNew = true;
          this.csvCustomFields.push(tmp);
        }else{
          let tmp = new CsvCustomField();
          tmp.isNew = true;
          this.csvCustomFields.push(tmp);
          return;
        }
      }*/
    }
  }
  deleteData(i,data,template){
    if(data.csvtempId!=null){
      this.service.isDelCustomField(data).subscribe(result=>{
        if(result.code == 0){
          this.massageList = result.data==null?[]:result.data;
          if(this.massageList.length>0){
            this.createBasicNotification(template);
          }else{
            this.csvCustomFields.splice(i,1);
          }
        }else{
          this.massageList = [];
        }
      })
    }else{
      this.csvCustomFields.splice(i,1);
    }
  }

  createBasicNotification(template: TemplateRef<{}>): void {
    this.notification.config({
      nzPlacement:'topLeft'
    });
    let option:NzNotificationDataOptions = { nzDuration:0,nzStyle: {
        width : '520px',
      },
      nzClass: 'test-class' };
    this.notification.template(template,option);
  }
}
