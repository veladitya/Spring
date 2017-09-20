import { Injectable, EventEmitter, Output } from '@angular/core';
import {HttpModule, Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class SignupService { 
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private bloggerUrl = '/api/saveUser';
   constructor(private http: Http){
     
   }
  
   registerUser(model) :  Observable<Object>{
      console.log("registerUser");
      return this.http.post(this.bloggerUrl, model, { headers: this.headers })
      .map((response) => {
        return response.json()
      });
   }
}