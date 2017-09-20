import { Injectable, EventEmitter, Output } from '@angular/core';
import {HttpModule, Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AsyncLocalStorage } from 'angular-async-local-storage';

@Injectable()
export class LoginService {
    
	private isLoggedIn:boolean = false;
    private redirectUrl:String;
    private _loggedInUser: Object = null;  
    //rest service parameters
  	private headers = new Headers({ 'Content-Type': 'application/json' });
  	private bloggerUrl = '/api/login';
    
   	constructor(private http: Http, protected storage: AsyncLocalStorage){
    	 
   	}
    
    public isAdmin():boolean {
        if (this.logggedInUserInformation() != null) {             
            if(this._loggedInUser != null && this._loggedInUser['userRole'] == 'ROLE_ADMIN'){
                return true;
            }
        }
        return false;
    }   

    public logggedInUserInformation(): Object {
        if(this._loggedInUser == null){
            this.getUserFromLocalStorage();
        }
        return this._loggedInUser;
    }

    public getUserFromLocalStorage() : void {
        this.storage.getItem('loggedInUser').subscribe((user) => {
           this._loggedInUser = user;
        }, () => {
            console.log('error ....')
        });
    }
    public setRedirectUrl(url:string) : void {
        this.redirectUrl = url;
    }
    
    public isValidUrl2Authenticate(url:string) : boolean {
        if(url == '/'){
            return true;
        }
        
        return this.isLoggedIn;
    }
    
    public loginUser(model) :  Observable<boolean> {        
      	return this.http.post(this.bloggerUrl, JSON.stringify(model), { headers: this.headers })
        .map((response) => {
            const loggedInUser = response.json();
            
            this.storage.setItem('loggedInUser', response.json()).subscribe(() => {
                 console.log("Saved to local");   
            }, () => {
                console.log("Error while saving to local");
            });

            if(loggedInUser["email"] == undefined){
                this.isLoggedIn = false;    
            }else {
                this.isLoggedIn = true;    
            }
            return  this.isLoggedIn;
        });
   }
}
