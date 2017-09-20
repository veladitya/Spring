import { Component, Injectable } from '@angular/core';
import { SignupService } from './signup.service';

/**
*  This class represents the lazy loaded SignupComponent.
*/

@Component({
  selector: 'app-signup-cmp',
  templateUrl: 'signup.component.html'
})

export class SignupComponent {
   constructor(private signupService: SignupService) { 
   
   } 
  
   onSubmit(model) {
    this.signupService.registerUser(model)  
    .subscribe(
      (response) => {
            console.log(response);
      },
      (error) => {
        console.log('Error happened' + error);
      }
    );
   }
  
   roles: any[] = [
    {id: 'ROLE_ADMIN', label: 'Admin'},
    {id: 'ROLE_USER', label: 'User'},
  ];

  
}