import { Component } from '@angular/core';
import {LoginService} from './login.service';
import { Router } from '@angular/router';

/**
*  This class represents the lazy loaded LoginComponent.
*/

@Component({
  selector: 'app-login-cmp',
  templateUrl: 'login.component.html'
})

export class LoginComponent {
  	constructor(private loginService: LoginService, public router: Router) { 
   
   	} 
  
  	onSubmit(model) {
    	this.loginService.loginUser(model)
            .subscribe(
              (isValid) => {
                    let redirect:string = '/dashboard/home';
                    if(isValid) {
                        this.router.navigate([redirect]);        
                    } else {
                        this.router.navigate(['']);
                    }
              },
              (error) => {
                console.log('Error happened' + error);
              }
        );
        
   }
 }
