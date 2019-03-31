import { Component, OnInit } from '@angular/core';
import {LocalStorageService} from "../../../service/localStorage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private lg:LocalStorageService,private router: Router,) { }

  ngOnInit() {
  }

  //退出登录
  exitLogin(){

    this.lg.clear(); //清理用户信息
    this.router.navigate(['home']);
  }
}
