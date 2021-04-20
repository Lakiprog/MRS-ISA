import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor
  (
    private router: Router,
    private http: HttpClient
  ) { }

  private access_token = null;
  private token_data = null;

  login(user: any) {
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.http.post<any>("http://localhost:8080/auth/login", body)
      .pipe(map((res) => {
        this.access_token = res.accessToken;
        this.token_data = res;
      }));
  }

  logout() {
    this.access_token = null;
    this.token_data = null;
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  setToken(token: any) {
    this.access_token = token.accessToken;
  }

  getTokenData() {
    if ( this.token_data != null) {
      return this.token_data;
    } else {
      return {
        id: 0,
        username: "",
        role: ""
      };
    }
  }

  setTokenData(tokenData : any) {
    this.token_data = tokenData;
  }
}
