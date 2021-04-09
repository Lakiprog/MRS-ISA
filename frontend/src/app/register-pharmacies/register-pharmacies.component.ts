import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { RegisterPharmaciesService } from './register-pharmacies.service';

@Component({
  selector: 'app-register-pharmacies',
  templateUrl: './register-pharmacies.component.html',
  styleUrls: ['./register-pharmacies.component.css']
})
export class RegisterPharmaciesComponent implements OnInit {

  pharmacyRegistrationForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  serverPharmacyAdmins = [
    {id: 10, email:'first@gmail.com', username: 'pharmacyAdmin1', password: '123', name: 'Pera', surmname: 'Peric', adress: 'asdf', city: 'asdf', country: 'asdf', phoneNumber: '0601234567'},
    {id: 11, email:'second@gmail.com', username: 'pharmacyAdmin2', password: '123', name: 'Mika', surmname: 'Mikic', adress: 'asdf', city: 'asdf', country: 'asdf', phoneNumber: '0601234568'},
    {id: 12, email:'third@gmail.com', username: 'pharmacyAdmin3', password: '123', name: 'Sara', surmname: 'Saric', adress: 'asdf', city: 'asdf', country: 'asdf', phoneNumber: '0601234569'}
  ]
  constructor(private fb: FormBuilder, private _registerPharmaciesService: RegisterPharmaciesService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.pharmacyRegistrationForm = this.fb.group(
      {
        name: ['', Validators.required],
        adress: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        description: [''],
        pharmacyAdmins: [[], Validators.required]
      }
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.pharmacyRegistrationForm.controls[controlName].hasError(errorName);
  }

  registerPharmacy() {
    console.log(this.pharmacyRegistrationForm.value);
    this._registerPharmaciesService.registerPharmacy(this.pharmacyRegistrationForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.pharmacyRegistrationForm.reset();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
