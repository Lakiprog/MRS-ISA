import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-dermatologist-home-page',
    templateUrl: './dermatologist-home-page.component.html',
    styleUrls: ['./dermatologist-home-page.component.css']
  })
  export class DermatologistHomePageComponent implements OnInit {
  
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
      this.router.navigate(["/DermatologistProfilePageComponent"]);
    }

    patients(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistPatientComponent"]);
      }
    }

    calendar(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistCalendarComponent"]);
      }
    }

    absence(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistAbsenceComponent"]);
      }
    }

    appointments(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistAppointmentsComponent"]);
      }
    }

    reservations(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistReservationsComponent"]);
      }
    }

    medicine(){
      if(!this.firstLogin){
        this.router.navigate(["/searchFilterMedicine"]);
      }
    }
  
  }