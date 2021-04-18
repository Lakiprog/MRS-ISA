import { Component, OnInit } from '@angular/core';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-pharmacist-home-page',
    templateUrl: './pharmacist-home-page.component.html',
    styleUrls: ['./pharmacist-home-page.component.css']
  })
  export class PharmacistHomePageComponent implements OnInit {
  
    constructor(private authService: AuthService) { }
  
    ngOnInit(): void {
    }

    logout() {
      this.authService.logout();
    }
  
  }