import { Route } from '@angular/router';

import { HomeRoutes } from './home/home.routes';
import { ChartRoutes } from './charts/chart.route';
import { TableRoutes } from './tables/table.routes';
import { UsersRoutes } from './users/users.routes';

import { DashboardComponent } from './index';


export const DashboardRoutes: Route[] = [
    {
      path: 'dashboard',
      component: DashboardComponent,
      children: [
        ...HomeRoutes,
        ...ChartRoutes,
        ...TableRoutes,
        ...UsersRoutes
      ]
    }
];
