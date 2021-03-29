import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { PasswordValidator } from './validators/passwordValidator';
import { RegistrationService } from './registration.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private fb: FormBuilder, private _registrationService: RegistrationService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  registrationForm! : FormGroup;
  EMAIL_REGEX : string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  systemAdmin : boolean = true;
  users = ['System administrator', 'Dermatologist', 'Pharmacy administrator', 'Supplier'];
  selected = this.systemAdmin ? 'System administrator' : 'Patient';
  oldPasswordValue : any;
  oldUserTypeValue : any;

  ngOnInit(): void {
    this.registrationForm = this.fb.group(
      {
        email: ['', [Validators.required, Validators.pattern(this.EMAIL_REGEX)]],
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        adress: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
        userType: [this.selected]
      }, {validator: PasswordValidator}
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.registrationForm.controls[controlName].hasError(errorName);
  }

  checkPasswords() {
    if (this.registrationForm.hasError('passwordMismatch')) {
      this.registrationForm.get('confirmPassword')?.setErrors([{'passwordMismatch': true}]);
    }
  }

  get confirmPassword() {
    return this.registrationForm.get('confirmPassword');
  }

  register() {
    this.oldPasswordValue = this.registrationForm.get('confirmPassword');
    this.oldUserTypeValue = this.registrationForm.get('userType');
    this.registrationForm.removeControl('confirmPassword');
    this.registrationForm.removeControl('userType');
    if (this.systemAdmin) {
      if (this.oldUserTypeValue.value === 'System administrator') {
        this._registrationService.registerSystemAdministrator(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
      } else if (this.oldUserTypeValue === 'Pharmacy administrator') {
        this._registrationService.registerPharmacyAdministrator(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
      } else if (this.oldUserTypeValue === 'Dermatologist') {
        this._registrationService.registerDermatologist(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
      } else if (this.oldUserTypeValue === 'Supplier') {
        this._registrationService.registerSupplier(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
      }
      this.registrationForm.addControl('userType', new FormControl(this.oldUserTypeValue?.value));
    } else {
      this._registrationService.registerPatient(this.registrationForm.value).subscribe(
        response => {
          this.openSnackBar(response, this.RESPONSE_OK);
          this.registrationForm.reset();
        },
        error => {
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
        }
      );
    }
    this.registrationForm.addControl('confirmPassword', new FormControl(this.oldPasswordValue?.value));
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}