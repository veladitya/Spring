import { Injectable, EventEmitter, Output } from '@angular/core';
import {HttpModule, Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class ApplicationService {
    
	//rest service parameters
  	private headers = new Headers({ 'Content-Type': 'application/json' });
  	private applicationUrl = '/api/allUsers';
    
   	constructor(private http: Http){
    	 
   	}
    
    public getUsers() :  Observable<any> {        
      	return this.http.get(this.applicationUrl)
        .map((response) => {
            return  response.json();
        });
   }
}
