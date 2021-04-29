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
  serverPharmacyAdmins : { id: string, username: string }[] = [];
  constructor(private fb: FormBuilder, private _registerPharmaciesService: RegisterPharmaciesService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.pharmacyRegistrationForm = this.fb.group(
      {
        name: ['', Validators.required],
        address: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        description: [''],
        pharmacyAdminsIds: [[], Validators.required]
      }
    );
    this._registerPharmaciesService.getPharmacyAdminsWithNoPharmacy().subscribe(
      data => {
        let list : { id: string, username: string }[] = [];
        Object.keys(data).forEach((key : string) => {
          const v = data[key];
          list.push({id: key, username: v});
        });
        this.serverPharmacyAdmins = list;
      }
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.pharmacyRegistrationForm.controls[controlName].hasError(errorName);
  }

  registerPharmacy() {
    this._registerPharmaciesService.registerPharmacy(this.pharmacyRegistrationForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.pharmacyRegistrationForm.reset();
        this._registerPharmaciesService.getPharmacyAdminsWithNoPharmacy().subscribe(
          data => {
            let list : { id: string, username: string }[] = [];
            Object.keys(data).forEach((key : string) => {
              const v = data[key];
              list.push({id: key, username: v});
            });
            this.serverPharmacyAdmins = list;
          }
        )
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
