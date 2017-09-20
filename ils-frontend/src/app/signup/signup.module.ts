import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {HttpModule, Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';

import { SignupComponent } from './signup.component';
import {SignupService} from './signup.service';

@NgModule({
    imports: [FormsModule, CommonModule, RouterModule, HttpModule],
    declarations: [SignupComponent],
    exports: [SignupComponent],
    providers: [SignupService],
})

export class SignupModule { }
