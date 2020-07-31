import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FeedComponent } from './components/feed/feed.component';
import { FollowersComponent } from './components/followers/followers.component';
import { FollowingComponent } from './components/following/following.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  { path: '', component: FeedComponent},
  { path: 'feed', component: FeedComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'following', component: FollowingComponent },
  { path: 'followers', component: FollowersComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
