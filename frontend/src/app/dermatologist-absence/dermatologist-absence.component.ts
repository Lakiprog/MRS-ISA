import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { DermatologistAbsenceService } from './dermatologist-absence.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dermatologist-absence',
  templateUrl: './dermatologist-absence.component.html',
  styleUrls: ['./dermatologist-absence.component.css']
})

export class DermatologistAbsenceComponent implements OnInit{
    constructor(private fb: FormBuilder, private service: DermatologistAbsenceService, private _snackBar: MatSnackBar, private router: Router) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  appointmentForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

    ngOnInit(): void {
        this.appointmentForm = this.fb.group({
            meetingTime: ['', Validators.required],
            endingTime: ['', Validators.required],
            description: ['']
        });
    }

    public hasError = (controlName: string, errorName: string) =>{
        return this.appointmentForm.controls[controlName].hasError(errorName);
    }

    checkTime(){
        const today = new Date(Date.now());
        const start = new Date(this.appointmentForm.get("meetingTime")?.value);
        if(start < today){
            this.appointmentForm.get("meetingTime")?.setErrors([{'timeMismatch': true}])
            return null;
        }
        const end = new Date(this.appointmentForm.get("endingTime")?.value);
        if(start > end){
            this.appointmentForm.get("endingTime")?.setErrors([{'timeMismatch': true}])
            return null;
        }
        start.setHours(start.getHours() + 2);
        end.setHours(end.getHours() + 2);
        return {"start" : start.toISOString(), "end" : end.toISOString(), "description" : this.appointmentForm.get("description")?.value};
    }

    public makeAbsence(){
        let appointment = this.checkTime();
        if(appointment){
            this.service.makeAbsence(appointment).subscribe(
                response => {
                  this.openSnackBar(response, this.RESPONSE_OK);
                  this.appointmentForm.reset();
                },
                error => {
                  this.openSnackBar(error.error, this.RESPONSE_ERROR);
                }
              );
        }
    }

    openSnackBar(msg: string, responseCode: number) {
        this._snackBar.open(msg, "x", {
          duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
          verticalPosition: this.verticalPosition,
          panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
        });
      }
    
}