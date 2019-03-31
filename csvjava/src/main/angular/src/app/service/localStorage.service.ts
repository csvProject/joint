import { Injectable } from '@angular/core';


@Injectable()
export class LocalStorageService {
  public localStorage:any;

  constructor() {
    if(!localStorage){
      throw new Error('已创建 local Storage');
    }
    this.localStorage = localStorage;
  }

   public set(key:string,value:any):void{
      this.localStorage[key] = JSON.stringify(value);
   }

   public get(key:string):any{
    if(this.localStorage[key] || false){
      return JSON.parse(this.localStorage[key]||'{}')
    }else{
      return false;
    }
   }

   public remove(key: string) {
    this.localStorage.removeItem(key);
   }

   //清除
  public clear(){
    this.localStorage.clear();
  }
}
