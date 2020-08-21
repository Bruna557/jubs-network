import { Component } from '@angular/core';
import { User } from './models/user';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Jubs';
  user: User;
  isLogged: boolean;

  constructor(private loginService: LoginService) {}

  ngOnInit() {
    
  }

  onSubmit() {
    this.loginService.login(this.user.userName, this.user.userPassword)
      .subscribe(response => {
        if (response) {
            localStorage.setItem('token', response.token);
            this.isLogged = true;
        } else {
            alert("Authentication failed.")
        }
    });
  }
}
