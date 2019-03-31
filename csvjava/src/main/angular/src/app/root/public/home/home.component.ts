import {AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../../service/login.service';
import {Observable} from "rxjs/index";
import {withIdentifier} from "codelyzer/util/astQuery";
import {UserInfo} from "../../../entity/UserInfo";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private renderer2: Renderer2,
    private router: Router,
    private loginService: LoginService) {

  }

  ngOnInit() {
    this.router.navigate(['home/login']);
  }

  userInfo: UserInfo = new UserInfo(); //用户信息
}
