import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    username: string;
    password: string;
    router: Router

    constructor(private userService: UserService, router: Router) {
        this.router = router;
    }

    login() {
        this.userService.login(this.username, this.password)
          .subscribe(response => {
            if (response) {
                localStorage.setItem('token', response);                
                localStorage.setItem('identity', this.username);
                console.log('user: ' + this.username);
                console.log('token: ' + response);
                this.router.navigate(['/feed']);
            } else {
                alert("Authentication failed.");
            }
        });
      }
}