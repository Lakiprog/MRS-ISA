import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { PasswordValidator } from './validators/passwordValidator';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private fb: FormBuilder, private _registrationService: RegistrationService) { }

  registrationForm! : FormGroup;
  EMAIL_REGEX : string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  errorMessage : string = "";
  successMessage : string = "";

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
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]]
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
    this.registrationForm.removeControl('confirmPassword');
    console.log(this.registrationForm.value);
    this._registrationService.register(this.registrationForm.value).subscribe(
        response => console.log('Success', this.successMessage = response),
        error => console.error('Error!', this.errorMessage = error.error)
      );
    this.registrationForm.addControl('confirmPassword', new FormControl());
    this.registrationForm.reset();
  }
}