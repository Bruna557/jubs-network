import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from './global';

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    public url: string;

    constructor(public _http: HttpClient) {
        this.url = GLOBAL.url;
    }

    login(username: string, password: string): Observable<any> {
        let params = JSON.stringify({
            userName: username,
            password: password
        });
        let headers = new HttpHeaders().set('Content-Type', 'application/json');

        return this._http.post('http://localhost:8080/login', params, {headers: headers});
    }
}