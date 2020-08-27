import { Component } from '@angular/core';
import { Post } from '../../models/post';
import { PostService } from '../../services/post.service';

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

  constructor(private postService: PostService) {}

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
    this.postService.createPost(this.userId, this.newPost)
      .subscribe( response => {
        if (response) {
          this.getPosts();
        } else {
          alert("Could not post :/");
        }
      });
  }

  like(postId: string) {
    this.postService.likePost(this.userId, postId)
      .subscribe(response => this.getPosts());
  }

  delete(postId: string) {
    this.postService.deletePost(this.userId, postId)
      .subscribe(response => this.getPosts());
  }
}