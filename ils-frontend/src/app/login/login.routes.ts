import { Routes } from '@angular/router';
import { LoginComponent } from './index';
import {LoginGuard} from "./login.guard";

export const LoginRoutes: Routes = [
    {
      path: '', component: LoginComponent, canActivate:[LoginGuard]
    }
];
