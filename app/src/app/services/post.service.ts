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
        let userId = parseInt(localStorage.getItem('identity'));
        this.url = GLOBAL.url + 'user/' + userId;
    }

    createPost(post: string): Observable<any> {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', this.userService.getToken());

        return this._http.post(this.url + '/post', post, {headers: headers});
    }

    getPosts(): Observable<any> {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());

        return this._http.get(this.url + '/feed', {headers: headers});
    }

    likePost(postId: string): Observable<any> {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());
        let params = new HttpParams()
            .append('postId', postId);

        return this._http.put(this.url + '/feed', {}, {headers: headers, params: params});
    }

    deletePost(postId: string) {
        let headers = new HttpHeaders()
            .set('Authorization', this.userService.getToken());
        let params = new HttpParams()
            .append('postId', postId);

        return this._http.delete(this.url + '/post', {headers: headers, params: params});
    }
}