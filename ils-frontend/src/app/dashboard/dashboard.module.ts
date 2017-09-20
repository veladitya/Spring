import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Ng2BootstrapModule } from 'ng2-bootstrap';
import { HomeModule } from './home/home.module';
import { ChartModule } from './charts/chart.module';
import { TableModule } from './tables/table.module';
import { DashboardComponent } from './dashboard.component';
import {TopNavComponent} from '../shared/index';
import {SidebarComponent} from '../shared/index';
import { UsersModule } from './users/users.module';



@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        Ng2BootstrapModule.forRoot(),
        HomeModule,
        ChartModule,
        TableModule,
        UsersModule
    ],
    declarations: [DashboardComponent, TopNavComponent, SidebarComponent],
    exports: [DashboardComponent, TopNavComponent, SidebarComponent]
})

export class DashboardModule { }
