import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PasswordValidator } from './passwordValidator';
import { ChangePatientDataService } from './change-patient-data.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-change-patient-data',
  templateUrl: './change-patient-data.component.html',
  styleUrls: ['./change-patient-data.component.css']
})
export class ChangePatientDataComponent implements OnInit {

  public checkOutForm: FormGroup;

    private Username : any;
    private Password :  any;
    private ConfirmPassword: any;

    RESPONSE_OK : number = 0;
    RESPONSE_ERROR : number = -1;

    verticalPosition: MatSnackBarVerticalPosition = "top";
    
  
    patient:any;
  
    constructor(private formBuilder: FormBuilder, private service:ChangePatientDataService, private snackBar: MatSnackBar){ 
      this.service.dataShow().subscribe((data:any) => {this.patient = data; console.log(this.patient)});

    this.checkOutForm = this.formBuilder.group({
      username: [''],
      password:[''],
      confirmPassword:['']
    },
    //{validator: PasswordValidator}
    );
  }


  public submitForm(){
    
    this.Username = this.checkOutForm.get("username");
    this.Password = this.checkOutForm.get("password");
    this.ConfirmPassword = this.checkOutForm.get("confirmPassword");
    
    this.checkOutForm.removeControl('confirmPassword');
    

    if(this.Password.value === '' || this.Password.value === null || this.Password.value === " "){
      this.checkOutForm.controls["password"].hasError("You didn't input password");
    }else if(this.Password.value !== this.ConfirmPassword.value){
      this.checkOutForm.controls["password"].hasError("Passwords don't match");
    }
    if(this.Username.value === '' || this.Username.value === null || this.Username.value === " "){
      this.checkOutForm.controls["username"].hasError("You didn't input username");
    }

    this.service.changeData(this.checkOutForm.value).subscribe(
      response => {
        console.log(response);
        this.openSnackBar(response, this.RESPONSE_OK);
        this.checkOutForm.reset();
      },
      error => {
        console.log(error);
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  ngOnInit(): void {
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
