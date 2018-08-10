import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-platformacctset',
  templateUrl: './platformacctset.component.html',
  styleUrls: ['./platformacctset.component.css']
})
export class PlatformacctsetComponent implements OnInit {
  titleList=['编辑平台信息','编辑用户信息'];

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
      this.accountList.push({id:j,name:'用户'+(j+1)+'号'})
    }
  }


  modalType = 1;

  showModal(i,plat): void {
    this.isVisible = true;
    this.modalType = plat.type;
  }

  handleOk(): void {
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
