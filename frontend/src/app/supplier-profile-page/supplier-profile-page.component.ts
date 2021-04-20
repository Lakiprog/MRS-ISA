import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
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
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor
  (
    private fb: FormBuilder, 
    private _supplierUpdateService: SupplierUpdateService, 
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.updateForm = this.fb.group(
      {
        username: [''],
        email: [''],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        adress: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
      }, {validator: PasswordValidator}
    );
    this._supplierUpdateService.getSupplierData().subscribe(
      data => {
        this.fillDataForm(data);
      }
    )
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.updateForm.controls[controlName].hasError(errorName);
  }

  checkPasswords() {
    if (this.updateForm.hasError('passwordMismatch')) {
      this.updateForm.get('confirmPassword')?.setErrors([{'passwordMismatch': true}]);
    }
  }

  get confirmPassword() {
    return this.updateForm.get('confirmPassword');
  }

  update() {
    console.log(this.updateForm.value);
    this._supplierUpdateService.updateSupplierData(this.updateForm.value).subscribe(
      response => {
        this.authService.setToken(response)
        this.authService.setTokenData(response)
        this._supplierUpdateService.getSupplierData().subscribe(
          data => {
            this.fillDataForm(data);
          }
        )
      },
      error => {
        this.openSnackBar(error.error.error, this.RESPONSE_ERROR);
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
    this.updateForm.get('password')?.setValue(data.password);
    this.updateForm.get('confirmPassword')?.setValue(data.password);
    this.updateForm.get('name')?.setValue(data.name);
    this.updateForm.get('surname')?.setValue(data.surname);
    this.updateForm.get('adress')?.setValue(data.adress);
    this.updateForm.get('city')?.setValue(data.city);
    this.updateForm.get('country')?.setValue(data.country);
    this.updateForm.get('phoneNumber')?.setValue(data.phoneNumber);
  }

  logout() {
    this.authService.logout();
  }

}
