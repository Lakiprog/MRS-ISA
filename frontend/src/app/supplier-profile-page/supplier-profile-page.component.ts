import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { PasswordValidator } from '../registration/validators/passwordValidator';
import { SupplierUpdateService } from './supplier-update.service';

@Component({
  selector: 'app-supplier-profile-page',
  templateUrl: './supplier-profile-page.component.html',
  styleUrls: ['./supplier-profile-page.component.css']
})
export class SupplierProfilePageComponent implements OnInit {

  updateForm! : FormGroup;
  updatePasswordForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor
  (
    private fb: FormBuilder, 
    private _supplierUpdateService: SupplierUpdateService, 
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.updateForm = this.fb.group(
      {
        username: [''],
        email: [''],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        adress: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
      }
    );
    this.updatePasswordForm = this.fb.group(
      {
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      }, {validator: PasswordValidator}
    );
    this._supplierUpdateService.getSupplierData().subscribe(
      data => {
        this.fillDataForm(data);
      }
    )
  }

  public hasError = (controlName: string, errorName: string, form: FormGroup) =>{
    return form.controls[controlName].hasError(errorName);
  }

  checkPasswords() {
    if (this.updatePasswordForm.hasError('passwordMismatch')) {
      this.updatePasswordForm.get('confirmPassword')?.setErrors([{'passwordMismatch': true}]);
    }
  }

  get confirmPassword() {
    return this.updatePasswordForm.get('confirmPassword');
  }

  update() {
    this._supplierUpdateService.updateSupplierData(this.updateForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this._supplierUpdateService.getSupplierData().subscribe(
          data => {
            this.fillDataForm(data);
          });
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  updatePassword() {
    this._supplierUpdateService.updatePassword(this.updatePasswordForm.value).subscribe(
      response => {
        this.authService.logout();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  fillDataForm(data: any) {
    this.updateForm.get('username')?.setValue(data.username);
    this.updateForm.get('email')?.setValue(data.email);
    this.updateForm.get('name')?.setValue(data.name);
    this.updateForm.get('surname')?.setValue(data.surname);
    this.updateForm.get('adress')?.setValue(data.adress);
    this.updateForm.get('city')?.setValue(data.city);
    this.updateForm.get('country')?.setValue(data.country);
    this.updateForm.get('phoneNumber')?.setValue(data.phoneNumber);
  }

  supplierWriteOffers() {
    this.router.navigate(["/supplierWriteOffers"]);
  }

  logout() {
    this.authService.logout();
  }

}
