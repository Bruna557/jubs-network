import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from './global';
import { Post } from '../models/post';

@Injectable({
    providedIn: 'root'
})
export class PostService {
    public url: string;

    constructor(public _http: HttpClient) {
        this.url = GLOBAL.url;
    }

    createPost(post: Post): Observable<any> {
        let params = JSON.stringify(post);
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', localStorage.getToken());

        return this._http.post(this.url + 'posts', params, {headers: headers});
    }

    getPosts(): Observable<any> {
        let headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', localStorage.getToken());

        return this._http.get(this.url + 'posts/', {headers: headers});
    }
}