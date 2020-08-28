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
      console.log("username: " + this.username);
      this.userService.login(this.username, this.password)
        .subscribe(response => {
          if (response) {
              localStorage.setItem('token', response);
              this.userService.getUserId(this.username)
                .subscribe(response => {
                  localStorage.setItem('identity', response);
                  localStorage.setItem('username', this.username);
                  console.log('user id: ' + localStorage.getItem('identity'));
                  console.log('token: ' + localStorage.getItem('token'));
                })
            this.router.navigate(['/feed']);
          } else {
              alert("Authentication failed.");
          }
      });
    }
}