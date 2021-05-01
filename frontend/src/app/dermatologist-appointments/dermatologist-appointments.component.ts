import { Component, OnInit } from '@angular/core';
import {DermatologistAppointmentsService} from './dermatologist-appointments.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dermatologist-appointments',
  templateUrl: './dermatologist-appointments.component.html',
  styleUrls: ['./dermatologist-appointments.component.css']
})
export class DermatologistAppointmentsComponent implements OnInit {

  constructor(private service : DermatologistAppointmentsService, private router: Router) { }

  events:any[] = [];

  ngOnInit(): void {
    this.service.getAppointmentsPharmacist().subscribe((data:any) => {this.events = data; this.addEvents(); console.log(this.events)});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    headerToolbar:{
        center: 'title',
        right: 'timeGridDay'
    },
    events: [],
    eventClick: info => this.handleEventClicked(info)
  };

  handleEventClicked(info:any){
    let app = {};
    this.service.getAppointmentWithId(info.event.id).subscribe(data=>{{app = data};
      this.router.navigate(['/DermatologistAppointmentInfoComponent'], {state: {data: {appointment : app, information : {comment:"", medication:[]}}}});
    });
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

}