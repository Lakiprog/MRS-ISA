import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-pharmacist-home-page',
    templateUrl: './pharmacist-home-page.component.html',
    styleUrls: ['./pharmacist-home-page.component.css']
  })
  export class PharmacistHomePageComponent implements OnInit {
  
    constructor(private authService: AuthService, private _snackBar: MatSnackBar, private router: Router) { }
    firstLogin = this.authService.getTokenData()?.firstLogin;
    RESPONSE_OK : number = 0;
    RESPONSE_ERROR : number = -1;
    verticalPosition: MatSnackBarVerticalPosition = "top";
  
    ngOnInit(): void {
      if (this.firstLogin) {
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    logout() {
      this.authService.logout();
    }

    openSnackBar(msg: string, responseCode: number, duration: number) {
      this._snackBar.open(msg, "x", {
        duration: responseCode === this.RESPONSE_OK ? duration : 20000,
        verticalPosition: this.verticalPosition,
        panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
      });
    }

    profile(){
      this.router.navigate(["/PharmacistProfilePageComponent"]);
    }

    patients(){
      if(!this.firstLogin){
        this.router.navigate(["/PharmacistPatientComponent"]);
      }
    }

    calendar(){
      if(!this.firstLogin){
        this.router.navigate(["/PharmacistCalendarComponent"]);
      }
    }

    absence(){
      if(!this.firstLogin){
        this.router.navigate(["/PharmacistAbsenceComponent"]);
      }
    }

    appointments(){
      if(!this.firstLogin){
        this.router.navigate(["/PharmacistAppointmentsComponent"]);
      }
    }

    reservations(){
      if(!this.firstLogin){
        this.router.navigate(["/PharmacistReservationsComponent"]);
      }
    }

    medicine(){
      if(!this.firstLogin){
        this.router.navigate(["/searchFilterMedicine"]);
      }
    }
  
  }