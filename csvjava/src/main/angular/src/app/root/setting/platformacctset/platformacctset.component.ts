import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-platformacctset',
  templateUrl: './platformacctset.component.html',
  styleUrls: ['./platformacctset.component.css']
})
export class PlatformacctsetComponent implements OnInit {
  titleList=['增加平台信息','编辑平台信息','增加用户信息','编辑用户信息'];

  selectPlatId;

  platList = [];
  accountList = [];
  isVisible = false;
  isConfirmLoading = false;

  constructor() { }

  ngOnInit() {
    for(let i =0;i<9;i++){
      this.platList.push({id:i,name:'平台'+(i+1)+'号',type:1})
    }

    for(let j =0;j<5;j++){
      this.accountList.push({id:j,name:'用户'+(j+1)+'号',type:3})
    }
  }

  clickPlat(indx,plat,platList:Array<any>){
    this.selectPlatId = plat.id;
  }

  delete(type,id){
    type==1?{

    }:{

    };
  }


  modalType = 1;

  showModal(i,plat): void {
    this.isVisible = true;
    this.modalType = plat.type;
  }
  pushElement(type){
    this.isVisible = true;
    this.modalType = type;
  }

  handleOk(modalType): void {
    this.isConfirmLoading = true;
    setTimeout(() => {
      this.isVisible = false;
      this.isConfirmLoading = false;
    }, 3000);
  }

  handleCancel(): void {
    this.isVisible = false;
  }
}
