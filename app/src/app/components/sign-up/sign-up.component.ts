import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  user: User = new User(null, null, null, null);

  constructor(private userService: UserService, private router: Router) { }

  register(): void {
    this.userService.registerUser(this.user)
        .subscribe(response => {
          this.router.navigate(['/login']);
        });
  }
}
