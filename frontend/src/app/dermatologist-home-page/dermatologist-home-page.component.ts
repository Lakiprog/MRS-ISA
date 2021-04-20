import { Component, OnInit } from '@angular/core';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-dermatologist-home-page',
    templateUrl: './dermatologist-home-page.component.html',
    styleUrls: ['./dermatologist-home-page.component.css']
  })
  export class DermatologistHomePageComponent implements OnInit {
  
    constructor(private authService: AuthService) { }
  
    ngOnInit(): void {
    }

    logout() {
      this.authService.logout();
    }
  
  }