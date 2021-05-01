import { Component, OnInit } from '@angular/core';
import {DermatologistChoosePredefinedService} from './dermatologist-choose-predefined.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
//import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
//import { Inject } from '@angular/core';

@Component({
  selector: 'app-dermatologist-choose-predefined',
  templateUrl: './dermatologist-choose-predefined.component.html',
  styleUrls: ['./dermatologist-choose-predefined.component.css']
})
export class DermatologistChoosePredefinedComponent implements OnInit {

  constructor(private service : DermatologistChoosePredefinedService, private _snackBar: MatSnackBar, private router:Router/*, public dialog: MatDialog*/) { }

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  events:any[] = [];

  ngOnInit(): void {
    this.service.getAppointmentsPredefinedDermatologist(history.state.data.appointment.pharmacy.id).subscribe((data:any) => {this.events = data; this.addEvents();});
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

  // let patient = {
  //  "id" : 1,
  //  "email" : "kaki@gmail.com",
  //  "name" : "Marko",
  //  "surname" : "Markuza",
  //  "address" : "Negde69",
  //  "city" : "NS",
  //  "country" : "Srbija",
  //  "phone_number" : "060602311",
  //  "username" : "kaki",
  //  "password" : "kaki"
 //   };
    console.log(history.state.data.appointment.patient);
    this.service.putAppointmentPredefinedDermatologist(info.event.id, history.state.data.appointment.patient).subscribe(response => {
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
    this.events.forEach(event => {
      if(Array.isArray(event['start'] )){
        event['start'] = new Date(event['start'][0], event['start'][1]-1, event['start'][2], event['start'][3], event['start'][4]);
      }else{
        event['start'].setMonth(event['start'].getMonth()-1);
      }
      if(Array.isArray(event['end'] )){
        event['end'] = new Date(event['end'][0], event['end'][1]-1, event['end'][2], event['end'][3], event['end'][4]);
      }else{
        event['end'].setMonth(event['end'].getMonth()-1);
      }
    });
    this.calendarOptions.events = this.events;
 }

 openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
    if(responseCode === this.RESPONSE_OK){
      this.router.navigate(['/DermatologistAppointmentInfoComponent'], {state: {data: {appointment : history.state.data.appointment, information : {comment: history.state.data.information.comment, medication:[]}}}});
    }
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