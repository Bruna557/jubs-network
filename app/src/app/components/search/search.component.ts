import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  users: User[];
  searchText: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.searchText = localStorage.getItem('search');
    this.userService.search()
      .subscribe(response => this.users = response);
  }

}
