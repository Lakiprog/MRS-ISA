import { Component, Inject, OnInit } from '@angular/core';
import {PharmacistAppointmentsService} from './pharmacist-appointments.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-pharmacist-appointments',
  templateUrl: './pharmacist-appointments.component.html',
  styleUrls: ['./pharmacist-appointments.component.css']
})
export class PharmacistAppointmentsComponent implements OnInit {

  constructor(private service : PharmacistAppointmentsService, private router: Router, public dialog : MatDialog) { }

  events:any[] = [];
  current:any;

  ngOnInit(): void {
    this.service.getAppointmentsPharmacist().subscribe((data:any) => {this.events = data; this.addEvents(); console.log(this.events)});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    headerToolbar:{
        center: 'title'
    },
    events: [],
    eventClick: info => this.handleEventClicked(info)
  };

  handleEventClicked(info:any){
    let app = {};
    
    this.current = info.event
    let dialogRef = this.dialog.open(DialogStartPharmacist, {
      data:  this.current
     });

     dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.service.getAppointmentWithId(info.event.id).subscribe(data=>{{app = data};
          this.router.navigate(['/PharmacistAppointmentInfoComponent'], {state: {data: {appointment : app, information : {comment:"", medication:[]}}}});
        });
      }
      else{
        dialogRef.close()
      }
    })

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

 back(){
  this.router.navigate(['/PharmacistHomePage']);
}

}

@Component({
  selector: 'start-dialog',
  templateUrl: 'start-dialog.html',
})
export class DialogStartPharmacist {
  constructor(
      public dialogRef: MatDialogRef<DialogStartPharmacist>,
      @Inject(MAT_DIALOG_DATA) public data:any
 ) {}
}