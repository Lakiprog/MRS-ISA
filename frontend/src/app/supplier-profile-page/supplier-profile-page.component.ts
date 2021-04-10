import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { PasswordValidator } from '../registration/validators/passwordValidator';
import { SupplierUpdateService } from './supplier-update.service';

@Component({
  selector: 'app-supplier-profile-page',
  templateUrl: './supplier-profile-page.component.html',
  styleUrls: ['./supplier-profile-page.component.css']
})
export class SupplierProfilePageComponent implements OnInit {

  updateForm! : FormGroup;
  EMAIL_REGEX : string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor(private fb: FormBuilder, private _supplierUpdateService: SupplierUpdateService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.updateForm = this.fb.group(
      {
        username: [''],
        email: ['', [Validators.required, Validators.pattern(this.EMAIL_REGEX)]],
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

  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
