import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChild } from '@angular/router';
import { LocalStorageService } from './service/localStorage.service';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(
    private route: Router,
    private ls: LocalStorageService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let path = route.routeConfig.path;
    const nextRoute = ['home', 'home/login', 'home/register'];

    if (nextRoute.indexOf(path.trim())<0) {
      if (!this.ls.get('userInfo')) {
        this.route.navigate(['home']);
        return false;
      }
    } else {
      if (this.ls.get('userInfo')) {
        this.route.navigate(['lfs/region']);
        return true;
      }
    }

    //非管理员用不可使用管理员页面
    let urlPathName = window.location.hash;
    if (urlPathName.indexOf('manage') >= 0) {
      if(this.ls.get('userInfo') == undefined){
        //this.route.navigate(['lfs/region']);
        return false;
      }
      if (this.ls.get('userInfo').roleId !== '1' && this.ls.get('userInfo').roleId !== 1) {
        //this.route.navigate(['lfs/region']);
        return false;
      }
    }

    if (path.indexOf('manage') >= 0) {
      if(this.ls.get('userInfo') == undefined){
        //this.route.navigate(['lfs/region']);
        return false;
      }
      if (this.ls.get('userInfo').roleId !== '1' && this.ls.get('userInfo').roleId !== 1) {
        //this.route.navigate(['lfs/region']);
        return false;
      }
    }

    return true;
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(route, state);
  }
}
