import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent {
  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getFollowed()
      .subscribe(response => {
        response.forEach(element => {
          this.userService.getUser(element.followedId)
            .subscribe(user => this.users.push(user));
        });
      });
  }

  unfollow() {
    
  }

}