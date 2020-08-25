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
  newPost: Post;
  user: User;

  constructor(private postService: PostService, private userService: UserService) {
    
  }

  ngOnInit(): void {
    this.getPosts();
    this.userService.getUser(1).subscribe(user => this.user = user);
  }

  getPosts(): void {
    this.postService.getPosts()
      .subscribe(posts => this.posts = posts)
  }

  post(): void {
    console.log("new post: " + this.newPost.postText)
    this.postService.createPost(userId)
  }
}