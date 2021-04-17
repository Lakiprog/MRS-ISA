import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { ApiService } from './api.service'
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor
  (
    private apiService: ApiService, 
    private userService: UserService,
    private router: Router
  ) { }

  private access_token = null;

  login(user: any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.apiService.post("http://localhost:8080/auth/login", JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        this.access_token = res.accessToken;
      }));
  }

  logout() {
    this.userService.currentUser = null;
    this.access_token = null;
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }
}
