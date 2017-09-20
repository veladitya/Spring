import { Component } from '@angular/core';
import { LoginService } from '../../login/login.service';

@Component({
  selector: 'app-sidebar-cmp',
  templateUrl: 'sidebar.html',
  providers: [LoginService]

})

export class SidebarComponent {
  isActive = false;
  showMenu = '';

  constructor(private loginService:LoginService) {

  }
  eventCalled() {
    this.isActive = !this.isActive;
  }
  addExpandClass(element: any) {
    if (element === this.showMenu) {
      this.showMenu = '0';
    } else {
      this.showMenu = element;
    }
  }
}
