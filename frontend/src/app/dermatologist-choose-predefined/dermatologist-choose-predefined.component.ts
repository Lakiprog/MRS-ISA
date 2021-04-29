import { Component, OnInit } from '@angular/core';
import {DermatologistChoosePredefinedService} from './dermatologist-choose-predefined.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { MatSnackBar } from '@angular/material/snack-bar';
//import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
//import { Inject } from '@angular/core';

@Component({
  selector: 'app-dermatologist-choose-predefined',
  templateUrl: './dermatologist-choose-predefined.component.html',
  styleUrls: ['./dermatologist-choose-predefined.component.css']
})
export class DermatologistChoosePredefinedComponent implements OnInit {

  constructor(private service : DermatologistChoosePredefinedService, private _snackBar: MatSnackBar/*, public dialog: MatDialog*/) { }

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  events = [];

  ngOnInit(): void {
    this.service.getAppointmentsPredefinedDermatologist().subscribe((data:any) => {this.events = data; console.log(this.events); this.addEvents();});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    headerToolbar:{
        left:'prev,next,today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    dateClick: this.handleDateClick.bind(this),
    events: [],
    eventClick: info => this.handleEventClicked(info)
  };

  handleEventClicked(info:any){
   // let dialogRef = this.dialog.open(DermatologistChoosePredefinedComponent, {
   //     height: '400px',
   //     width: '600px',
   //     data: { dialogTitle: info.event.title, start:info.event.start, end:info.event.end }
   //  });
   let patient = {
    "id" : 1,
    "email" : "kaki@gmail.com",
    "name" : "Marko",
    "surname" : "Markuza",
    "address" : "Negde69",
    "city" : "NS",
    "country" : "Srbija",
    "phone_number" : "060602311",
    "username" : "kaki",
    "password" : "kaki"
    };

    this.service.putAppointmentPredefinedDermatologist(info.event.id, patient).subscribe(response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        info.event.remove();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      })
  }

  handleDateClick(arg:any) {
  }

  addEvents(){
    this.calendarOptions.events = this.events;
 }

 openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}

//@Component({
 //   selector: 'dialog',
 //   templateUrl: 'dialog.html',
 // })
 // export class Dialog {
 //   constructor(
  //      public dialogRef: MatDialogRef<DermatologistChoosePredefinedComponent>,
  //      @Inject(MAT_DIALOG_DATA) public data:any
  //   ) {}
     
   //  ngOnInit(): void {
   //   console.log(this.data) // Here the data you passed through the method open
   //  }
  //}