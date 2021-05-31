import { Component, OnInit } from '@angular/core';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';

@Component({
  selector: 'app-public-pharmacy-profile-page',
  templateUrl: './public-pharmacy-profile-page.component.html',
  styleUrls: ['./public-pharmacy-profile-page.component.css'],
})
export class PublicPharmacyProfilePageComponent implements OnInit {
  pharmacy!: Pharmacy;
  constructor(private location: Location, private authService: AuthService) {}

  ngOnInit(): void {
    this.pharmacy = JSON.parse(localStorage.getItem('pharmacy') || '{}');
  }

  back(): void {
    localStorage.removeItem('pharmacy');
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
