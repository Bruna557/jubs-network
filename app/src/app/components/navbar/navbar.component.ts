import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  searchText: string;

  constructor(private router: Router) {}

  search() {    
    localStorage.setItem('search', this.searchText);
    location.reload();
    this.router.navigate(['/search']);
  }
}
