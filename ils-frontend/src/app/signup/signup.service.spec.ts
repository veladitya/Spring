import { TestBed, inject } from '@angular/core/testing';

import { SignupService } from './signup.service';
import { Http, HttpModule } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';

describe('SignupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpModule
      ],
      providers: [SignupService]
    });
  });

  it('should be created', inject([SignupService], (signupService: SignupService) => {
    let responseValue:any = null;
    let self  = this;
    signupService.registerUser({name:"admin"})
    .subscribe(
      (response) => {
        self.responseValue = response;        
      },
      (error) => {
        console.log('Error happened' + error);
        self.responseValue = error;
      }
    );    
    expect(responseValue).toEqual(true);
  }));
  
});


