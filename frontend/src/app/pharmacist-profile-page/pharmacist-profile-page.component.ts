import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { PasswordValidator } from '../registration/validators/passwordValidator';
import { PharmacistUpdateService } from './pharmacist-update.service';

@Component({
  selector: 'app-pharmacist-profile-page',
  templateUrl: './pharmacist-profile-page.component.html',
  styleUrls: ['./pharmacist-profile-page.component.css']
})
export class PharmacistProfilePageComponent implements OnInit {

  updateForm! : FormGroup;
  EMAIL_REGEX : string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor(private fb: FormBuilder, private _pharmacistUpdateService: PharmacistUpdateService, private _snackBar: MatSnackBar) { }
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
        rating: [''],
      }, {validator: PasswordValidator}
    );
    this._pharmacistUpdateService.getPharmacistData().subscribe(
      data => {
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
        this.updateForm.get('rating')?.setValue(data.rating);
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
    this._pharmacistUpdateService.updatePharmacistData(this.updateForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this._pharmacistUpdateService.getPharmacistData().subscribe(
          data => {
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
            this.updateForm.get('rating')?.setValue(data.rating);
          }
        )
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

}
