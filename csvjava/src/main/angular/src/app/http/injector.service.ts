import {Injectable, Injector} from '@angular/core';

@Injectable()
export class InjectorService {
  constructor(private injector: Injector){}
  getInjector(){
    return this.injector
  }
}
