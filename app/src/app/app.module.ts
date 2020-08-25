import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { FeedComponent } from './components/feed/feed.component';
import { FollowersComponent } from './components/followers/followers.component';
import { FollowingComponent } from './components/following/following.component';
import { ProfileComponent } from './components/profile/profile.component';

import { PostService } from './services/post.service';
import { UserService } from './services/user.service';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    LoginComponent,
    FeedComponent,
    FollowersComponent,
    FollowingComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    MatIconModule,
    MatCardModule,
  ],
  providers: [
    UserService,
    PostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
