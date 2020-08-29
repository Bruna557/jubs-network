import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  user: User = new User(null, null, null, null);
  change: string = null;
  newValue: string = null;

  constructor(private userService: UserService, private router: Router) {
    this.userService.getUser()
      .subscribe(response => {
        this.user = response;
      });
  }

  edit() {
    switch(this.change) {
      case "description": {
        this.userService.changeDescription(this.newValue).subscribe(response => {

        });
        break;
      }
      case "picture": {
        this.userService.changePicture(this.newValue).subscribe(response => {

        });
        break;
      }
      case "password": {
        this.userService.changePassword(this.newValue).subscribe(response => {

        });
        break;
      }
      default: { break; }
    }
    this.change = null;
    this.newValue = null;
    this.router.navigate(['/profile']);
  }

  delete() {
    this.userService.deleteUser()
      .subscribe(response => {
        this.router.navigate(['/login']);
      });
  }
}