import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {LoginService} from './login.service';

@Injectable()
export class LoginGuard implements CanActivate{

	constructor(private loginService: LoginService, private router: Router) { 
   
   	}	 
  
	canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    	const url: string = state.url;
      	return this.checkIfLoggedIn(url);
    }

	private checkIfLoggedIn(url: string): boolean{
		let loggedIn:boolean =  this.loginService.isValidUrl2Authenticate(url);
        console.log("---");
        // Store the attempted URL for redirecting
        this.loginService.setRedirectUrl(url);
        if(!loggedIn){            
        	console.log("LoginGuard: The user is not logged in and can't navigate to respective component");
          	// Navigate to the login page with extras
          	this.router.navigate(['']);
        }

        return loggedIn;
    }
}