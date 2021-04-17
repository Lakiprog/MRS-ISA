import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ApiService } from './api.service'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser: any;

  constructor(private apiService: ApiService) {}

  getCurrentUserInfo() {
    return this.apiService.get("http://localhost:8080/api/getCurrentUserData")
      .pipe(map(user => {
        this.currentUser = user;
        return user;
      }));
  }
}
