import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { UserService } from './user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm! : FormGroup;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor
  (
    private fb: FormBuilder, 
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group(
      {
        username: ['', Validators.required],
        password: ['', Validators.required],
      }
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.loginForm.controls[controlName].hasError(errorName);
  }

  login() {
    this.authService.login(this.loginForm.value)
      .subscribe(
        data => {
          this.userService.getCurrentUserInfo().subscribe();
        },
        error => {
          this.openSnackBar()
        });
    }

    openSnackBar() {
      this._snackBar.open("Invalid username/password", "x", {
        duration: 20000,
        verticalPosition: this.verticalPosition,
        panelClass: "back-red"
      });
    }

}
