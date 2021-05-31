import { Component, OnInit } from '@angular/core';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { PublicPharmacyProfileService } from './public-pharmacy-profile.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-public-pharmacy-profile-page',
  templateUrl: './public-pharmacy-profile-page.component.html',
  styleUrls: ['./public-pharmacy-profile-page.component.css'],
})
export class PublicPharmacyProfilePageComponent implements OnInit {

  pharmacy!: Pharmacy;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  
  constructor
  (
    private location: Location, 
    private authService: AuthService,
    private publicPharmacyProfilePageService: PublicPharmacyProfileService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.pharmacy = JSON.parse(localStorage.getItem('pharmacy') || '{}');
  }

  subscribeToPromotions() {
    this.publicPharmacyProfilePageService.subscribeToPromotions(this.pharmacy).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
      }
    );
  }

  back(): void {
    localStorage.removeItem('pharmacy');
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
