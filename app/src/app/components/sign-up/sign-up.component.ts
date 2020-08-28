import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  username: string;
  email: string;
  description: string;
  picture: string;
  password: string;

  constructor() { }

  register(): void {
  }

}
