import { Component, OnInit, ViewChild } from '@angular/core';
import {DermatologistUsersService} from './dermatologist-users.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';;
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-dermatologist-users',
  templateUrl: './dermatologist-users.component.html',
  styleUrls: ['./dermatologist-users.component.css']
})
export class DermatologistUsersComponent implements OnInit {

  constructor(private service : DermatologistUsersService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar, private authService: AuthService) { }

  @ViewChild(MatPaginator)
  paginatorPatients!: MatPaginator;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  patientsForm! : FormGroup;
  displayedColumnsPatients: string[] = ['name', 'surname', 'username', 'email', 'adress', 'city', 'country', 'phoneNumber'];
  patientsData:any = [];
  patientsDataSource = new MatTableDataSource<any>(this.patientsData);

  ngOnInit(): void {
      this.patientsForm = this.fb.group(
      {
        nameSearch: [''],
        surnameSearch: ['']
      });
      this.getPatients("", "");
    }

  ngAfterViewInit(): void {
    this.patientsDataSource.paginator = this.paginatorPatients;
  }

   getPatients(name:string, surname:string){
    this.service.getPatients(name, surname).subscribe(
      response =>{
        this.patientsData = response;
        this.patientsDataSource = new MatTableDataSource<any>(this.patientsData);
        this.patientsDataSource.paginator = this.paginatorPatients;
      }
    );
   }

  filter(){
    this.getPatients(this.patientsForm.get("nameSearch")?.value, this.patientsForm.get("surnameSearch")?.value);
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
  
  logout() {
    this.authService.logout();
  }
  
  profile(){
    this.router.navigate(["/DermatologistProfilePageComponent"]);
  }
  
  patients(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistPatientComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  allPatients(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistUsersComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  calendar(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistCalendarComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  absence(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistAbsenceComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  appointments(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistAppointmentsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  reservations(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistReservationsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  medicine(){
    if(!this.firstLogin){
      this.router.navigate(["/searchFilterMedicine"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  back(){
    this.router.navigate(['/DermatologistHomePage']);
  }
}