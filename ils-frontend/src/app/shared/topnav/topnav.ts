import { Component, OnInit } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import { AsyncLocalStorage } from 'angular-async-local-storage';
import { LoginService } from '../../login/login.service';

declare var $: any;

@Component({
    selector: 'app-top-nav',
    templateUrl: 'topnav.html',
    providers: [LoginService]
})

export class TopNavComponent implements OnInit{

    constructor(protected storage: AsyncLocalStorage, private router: Router,  private loginService: LoginService) {
    }
  
    ngOnInit() {
      
    }  

    logoutEventHandler(buttonId: string): void {
        this.storage.clear().subscribe(() => {
            console.log('session cleared');
        }, () => {});
        this.router.navigate(["/"]);
    }
}
