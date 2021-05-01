import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-system-admin-profile-page',
  templateUrl: './system-admin-profile-page.component.html',
  styleUrls: ['./system-admin-profile-page.component.css']
})
export class SystemAdminProfilePageComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  registerUsers() {
    this.router.navigate(['/register']);
  }

  registerPharmacies() {
    this.router.navigate(['/registerPharmacies']);
  }

  addMedicine() {
    this.router.navigate(['/addMedicine']);
  }

  respondToComplaints() {
    this.router.navigate(['/respondToComplaints']);
  }

  logout() {
    this.authService.logout();
  }

}
