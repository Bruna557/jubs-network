import { Component } from '@angular/core';
import { Post } from '../../models/post';
import { PostService } from '../../services/post.service';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent {
  posts: Post[];
  postOwner: User = new User(null, null, null, null);
  newPost: string;
  user: User = new User(null, null, null, null);

  constructor(private postService: PostService, private userService: UserService) {}

  ngOnInit(): void {
    this.getUser();
    this.getPosts();
  }

  getUser(): void {
    this.userService.getUser()
      .subscribe(response => {
        this.user = response;
      });
  }

  getPosts(): void {
    this.postService.getPosts()
      .subscribe(posts => this.posts = posts);
  }

  post(): void {
    this.postService.createPost(this.newPost)
      .subscribe( response => {
        if (response) {
          this.getPosts();
        } else {
          alert("Could not post :/");
        }
      });
    this.newPost = null;
  }

  like(postId: string) {
    this.postService.likePost(postId)
      .subscribe(() => this.getPosts());
  }

  delete(postId: string) {
    this.postService.deletePost(postId)
      .subscribe(() => this.getPosts());
  }
}