import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {HttpModule, Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';
import { LoginComponent } from './login.component';
import { LoginService } from './login.service';
import {LoginGuard} from "./login.guard";
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from '../app.auth.interceptor';
import { AsyncLocalStorageModule } from 'angular-async-local-storage';

@NgModule({
    imports: [FormsModule, CommonModule, RouterModule, HttpModule, AsyncLocalStorageModule],
    declarations: [LoginComponent],
    exports: [LoginComponent],
    providers: [{
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true,
      }, LoginService, LoginGuard]
    
})

export class LoginModule { }
