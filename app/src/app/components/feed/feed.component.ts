import { Component } from '@angular/core';
import { Post } from '../../models/post';
import { PostService } from '../../services/post.service';
import { UserService } from '../../services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent {
  posts: Post[];
  newPost: string;
  userId: string;
  username: string;

  constructor(private postService: PostService, private userService: UserService) {
    
  }

  ngOnInit(): void {
    this.userId = localStorage.getItem('identity');
    this.username = localStorage.getItem('username');
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getPosts(this.userId)
      .subscribe(posts => this.posts = posts)
  }

  post(): void {
    console.log("new post: " + this.newPost)
    this.postService.createPost(this.userId, this.newPost)
      .subscribe(response => console.log(response));
  }
}