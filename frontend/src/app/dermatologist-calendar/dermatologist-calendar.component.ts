import { Component, Inject, OnInit } from '@angular/core';
import {DermatologistCalendarService} from './dermatologist-calendar.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dermatologist-calendar',
  templateUrl: './dermatologist-calendar.component.html',
  styleUrls: ['./dermatologist-calendar.component.css']
})
export class DermatologistCalendarComponent implements OnInit {

  constructor(private service : DermatologistCalendarService, public dialog: MatDialog, public router : Router) { }

  events:any[] = [];
  pharmacies:any[] = [];
  current:any;
  pharmaci:any;

  ngOnInit(): void {
    this.service.getAppointmentsDermatologist().subscribe((data:any) => {this.events = data; this.addEvents();});
    this.service.getEmployments().subscribe((data:any) =>{
      data.forEach((employment:any) => {
        this.pharmacies.push(employment.pharmacy);
      });
    })
    this.pharmaci = "All";
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
    eventClick: info => this.openDialog(info)
  };

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

 openDialog(info:any) {
  this.current = info.event;
  this.dialog.open(DialogDataExampleDialogDermatologist, {
    data: this.current,
    disableClose: false
  });
}

back(){
  this.router.navigate(['/DermatologistHomePage']);
}

change(){
  if( typeof this.pharmaci === 'string'){
    this.service.getAppointmentsDermatologist().subscribe((data:any) => {this.events = data; this.addEvents();});
  }else{
    this.service.getAppointmentsPharmacy(this.pharmaci.id).subscribe((data:any) => {this.events = data; this.addEvents();});
  }
}

}

@Component({
  selector: 'appointments-dialog',
  templateUrl: 'appointments-dialog.html',
})
export class DialogDataExampleDialogDermatologist {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<DialogDataExampleDialogDermatologist>) {}

  public close(): void {
    this.dialogRef.close();
  }
}