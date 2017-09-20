import { Component } from '@angular/core';
import { NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';

/**
*  This class represents the lazy loaded DashboardComponent.
*/

@Component({
  selector: 'app-dashboard-cmp',
  templateUrl: 'dashboard.component.html'
})

export class DashboardComponent { 
  navigating: boolean = false;
  constructor (private router: Router){
    this.router.events.subscribe(
    (event) => this.eventLogger(event)
    );
  }

  eventLogger(event){
    if (event instanceof NavigationStart){
      this.navigating=true;
    }
    if (event instanceof NavigationEnd ||
        event instanceof NavigationError || 
        event instanceof NavigationCancel){
      this.navigating=false;      
    }
  }
}
