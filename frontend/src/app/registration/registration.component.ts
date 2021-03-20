import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { PasswordValidator } from './validators/passwordValidator';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  registrationForm! : FormGroup;

  ngOnInit(): void {
    this.registrationForm = this.fb.group(
      {
        email: ['', Validators.required],
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: [''],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        gender: [''],
        address: ['', Validators.required],
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
    else {
      this.registrationForm.get('confirmPassword')?.setErrors(null);
    }
  }

  get confirmPassword() {
    return this.registrationForm.get('confirmPassword');
  }
}
