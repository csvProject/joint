import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfo } from '../../../entity/UserInfo';
import { LoginService } from '../../../service/login.service';
import { LocalStorageService } from '../../../service/localStorage.service';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  validateForm: FormGroup;
  userInfo: UserInfo = new UserInfo(); //用户信息

  ngOnInit(): void {

    this.validateForm = this.fb.group({
      userName: [ null, [ Validators.required ] ],
      password: [ null, [ Validators.required ] ],
    });
  }

  message:any='';//登录返回消息

  constructor(private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private lg:LocalStorageService) {

  }


  // 登录
  login(info?: UserInfo) {

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }


    if(undefined == this.userInfo.username || null == this.userInfo.username){
      return;
    }
    if(undefined == this.userInfo.password || null == this.userInfo.password){
      return;
    }

    //this.router.navigate(['lfs/csvexport']);
    //用户密码加密
    this.loginService.login(this.userInfo).subscribe(data=>{
      this.lg.set("userInfo", null);
      if(data.code == 0){//登录成功
        this.lg.set("userInfo",data.data);//设置用户登录成功后返回信息
        this.lg.set("token",data.data.token); //设置token信息
        this.router.navigate(['lfs/csvexport']);

      }else if(data.code == 1){
        this.message = data.msg;
      }else if(data.code == -1){
        this.message = data.msg;
      }
    },error=>{

    });

  }
}



