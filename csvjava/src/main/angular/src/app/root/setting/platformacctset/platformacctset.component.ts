import { Component, OnInit } from '@angular/core';
import { PlatformacctService } from '../../../http/platformacct.service';
import {AccountInfo, PlatInfo} from "../../../entity/setData";
import {CurrencyUtil} from "../../../util/currencyUtil";

@Component({
  selector: 'app-platformacctset',
  templateUrl: './platformacctset.component.html',
  styleUrls: ['./platformacctset.component.css']
})
export class PlatformacctsetComponent implements OnInit {
  constructor(private service:PlatformacctService) { }
  titleList=['增加平台信息','编辑平台信息','增加用户信息','编辑用户信息'];
  modalType = 1;
  platInfo:PlatInfo = new PlatInfo();
  accountInfo:AccountInfo = new AccountInfo();

  pfnm:string='';
  selectPlatIndex = 0;

  platList = [];
  accountList = [];
  isVisible = false;
  isConfirmLoading = false;

  ngOnInit() {
    this.getPlatInfos(this.pfnm,this.selectPlatIndex);
  }

  clickPlat(indx,plat): void {
    this.selectPlatIndex = indx;
    this.getPfaccountInfo(plat.platformId);
  }

  delete(type,id){
    type==0?this.deletePlatInfoById(id):this.deletePfaccountInfoById(id);
  }

  selectPlat(pfnm){
    this.getPlatInfos(pfnm,0);
  }

  private deletePlatInfoById(platformId){
    this.selectPlatIndex = 0 ;
    this.service.delPlatInfoByPfid(platformId).subscribe(result =>{
      if(result.code == 0){
        this.getPlatInfos(this.pfnm,this.selectPlatIndex);
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }
  private deletePfaccountInfoById(pfaccountId){
    this.service.delPfaccountInfoByPfacid(pfaccountId).subscribe(result=>{
      if(result.code == 0){
        this.getPfaccountInfo(this.platList[this.selectPlatIndex].platformId);
      }else if(result.code == 1){

      }else {
        console.error(result.msg);
      }
    });

  }
  private getPlatInfos(pfnm,selectPlatIndex){
    this.service.getPlatInfo(pfnm).subscribe(result=>{
      console.log(result);
      if(result.code == 0){
        this.platList = result.data == null?[]:result.data;
        // this.platList.forEach(value => value.checked = false);
        this.platList[selectPlatIndex] != null? this.getPfaccountInfo(this.platList[selectPlatIndex].platformId):this.accountList=[];
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }
  private getPfaccountInfo(pfid): void {
    this.service.getPfaccountInfo(pfid).subscribe(result=>{
      if(result.code == 0){
        this.accountList = result.data;
      }else if(result.code == 1){

      }else{
        console.error(result.msg);
      }
    })
  }

  showModal(i,type,modalData?): void {

    this.isVisible = true;
    this.modalType = type;
    if(type == 0){
      this.platInfo = new PlatInfo();
    }else if(type == 1){
      this.platInfo = Object.assign({},modalData);
    }else if(type == 2){
      this.accountInfo = new AccountInfo();
    }else if(type == 3){
      this.accountInfo = Object.assign({},modalData);
    }
  }

  handleOk(modalType): void {
    this.isConfirmLoading = true;
    if(modalType == 0){
      this.service.insertPlatInfo(this.platInfo).subscribe(result=>{
        if(result.code == 0){
          this.isVisible = false;
          this.isConfirmLoading = false;
          this.getPlatInfos(this.pfnm,this.selectPlatIndex);
        }else if(result.code == 1){
        }else{
          console.error(result.msg);
        }
      });
    }else if(modalType == 1){
      this.service.upDatePlatInfo(this.platInfo).subscribe(result=>{
        if(result.code == 0){
          this.isVisible = false;
          this.isConfirmLoading = false;
          this.getPlatInfos(this.pfnm,this.selectPlatIndex);
        }else if(result.code == 1){
        }else{
          console.error(result.msg);
        }
      });
    }else if(modalType == 2){
      this.accountInfo.platformId = this.platList[this.selectPlatIndex].platformId;
      this.platList[this.selectPlatIndex] != null?
      this.service.insertPfaccountInfo(this.accountInfo).subscribe(result=>{
        if(result.code == 0){
          this.isVisible = false;
          this.isConfirmLoading = false;
          this.getPlatInfos(this.pfnm,this.selectPlatIndex);
        }else if(result.code == 1){
        }else{
          console.error(result.msg);
        }
      }):null;
    }else if(modalType == 3){
      this.service.upDatePfaccountInfo(this.accountInfo).subscribe(result=>{
        if(result.code == 0){
          this.isVisible = false;
          this.isConfirmLoading = false;
          this.getPlatInfos(this.pfnm,this.selectPlatIndex);
        }else if(result.code == 1){
        }else{
          console.error(result.msg);
        }
      });
    }
  }

  handleCancel(): void {
    this.isVisible = false;
  }
}
