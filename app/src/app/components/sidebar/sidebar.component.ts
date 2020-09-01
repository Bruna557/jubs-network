import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  router: Router;
  constructor(router: Router, private userService: UserService) {
      this.router = router;
  }

  logout() {
    this.userService.logout()
      .subscribe(() => {
        localStorage.clear();
        this.router.navigate(['/login']);
    });    
  }
}