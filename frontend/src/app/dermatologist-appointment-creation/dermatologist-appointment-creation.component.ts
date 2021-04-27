import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { DermatologistAppointmentCreationService } from './dermatologist-appointment-creation.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dermatologist-appointment-creation',
  templateUrl: './dermatologist-appointment-creation.component.html',
  styleUrls: ['./dermatologist-appointment-creation.component.css']
})

export class DermatologistAppointmentCreationComponent implements OnInit{
    constructor(private fb: FormBuilder, private _dermatologistAppointmentCreationService: DermatologistAppointmentCreationService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  appointmentForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  dermatologist = {};
  pharmacy = {};

    ngOnInit(): void {
        this.appointmentForm = this.fb.group({
            meetingTime: ['', Validators.required],
            endingTime: ['', Validators.required],
        });
        this._dermatologistAppointmentCreationService.getPharmacistData().subscribe((data:any) => {this.dermatologist = data;});
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
        let spliting = this.appointmentForm.get("endingTime")?.value.split(':')
        const end = new Date(start.getFullYear(), start.getMonth(), start.getDate(), spliting[0], spliting[1]);
        if(start > end){
            this.appointmentForm.get("endingTime")?.setErrors([{'timeMismatch': true}])
            return null;
        }
        start.setHours(start.getHours() + 2);
        end.setHours(end.getHours() + 2);
        return {"start" : start.toISOString(), "end" : end.toISOString(), "patient" : {}, "dermatologist" : this.dermatologist, "pharmacy" : this.pharmacy, "price" : 0};
    }

    public makeAppointment(){
        let appointment = this.checkTime();
        if(appointment){
            const patient = {"id" : 1,
            "email" : "kaki@gmail.com",
            "name" : "Marko",
            "surname" : "Markuza",
            "address" : "Negde69",
            "city" : "NS",
            "country" : "Srbija",
            "phone_number" : "060602311",
            "username" : "kaki",
            "password" : "kaki"}

            appointment["patient"] = patient;
            appointment["pharmacy"] = {"id" : 2};
            this._dermatologistAppointmentCreationService.makeAppointment(appointment).subscribe(
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