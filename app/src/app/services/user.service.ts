import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from './global';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public url: string;
  public token;

  constructor(public _http: HttpClient) { 
    this.url = GLOBAL.url;
  }

  login(username: string, password: string): Observable<any> {
    let headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded');
    let data = 'username=' + username + '&password=' + password;

    return this._http.post(this.url + 'login', data, {headers, responseType: 'text'});
  }

  getUserId(username: string): Observable<any> {
    let headers = new HttpHeaders()
      .set('Authorization', this.getToken());
    let params = new HttpParams()
      .append('username', username);

    return this._http.get(this.url + 'user', {headers: headers, params: params, responseType: 'text'});
  }

  getToken() {
    let token = localStorage.getItem('token');

    if (token != "undefined") {
        this.token = token;
    } else {
        this.token = null;
    }

    return this.token;
  }
}
