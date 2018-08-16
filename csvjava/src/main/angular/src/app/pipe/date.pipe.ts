import { Pipe, PipeTransform } from '@angular/core';
import { CurrencyUtil } from '../util/currencyUtil';

@Pipe({
  name: 'DatePipe'
})
export class DatePipe implements PipeTransform{
  constructor(
    private util: CurrencyUtil,
  ) {}

  // isSpecial为true 特殊处理
  transform(date: string,format:string):string{
    console.log(date);
    if (date == undefined || date == null || date =='') {
      return '';
    }else{
      format = format.replace(/H/g,'h');
      let res =  this.util.dateFormat(new Date(date),format);
      return res;
    }
  }

}
