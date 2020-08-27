import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from './global';
import { UserService } from './user.service';

@Injectable({
    providedIn: 'root'
})
export class PostService {
    public url: string;

    constructor(public _http: HttpClient, private userService: UserService) {
        this.url = GLOBAL.url;
    }

    createPost(userId: string, post: string): Observable<any> {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', this.userService.getToken());

        return this._http.post(this.url + 'user/' + userId + '/post', post, {headers: headers});
    }

    getPosts(userId: string): Observable<any> {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());

        return this._http.get(this.url + 'user/' + userId + '/feed', {headers: headers});
    }

    likePost(userId: string, postId: string): Observable<any> {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());
        let params = new HttpParams()
            .append('postId', postId);

        return this._http.put(this.url + 'user/' + userId + '/feed', {}, {headers: headers, params: params});
    }

    deletePost(userId: string, postId: string) {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());
        let params = new HttpParams()
            .append('postId', postId);

        return this._http.delete(this.url + 'user/' + userId + '/post', {headers: headers, params: params});
    }
}