import { Component, OnInit } from '@angular/core';
import {DermatologistCalendarService} from './dermatologist-calendar.service'
import { CalendarOptions } from '@fullcalendar/angular';

@Component({
  selector: 'app-dermatologist-calendar',
  templateUrl: './dermatologist-calendar.component.html',
  styleUrls: ['./dermatologist-calendar.component.css']
})
export class DermatologistCalendarComponent implements OnInit {

  constructor(private service : DermatologistCalendarService) { }

  events:any[] = [];

  ngOnInit(): void {
    this.service.getAppointmentsDermatologist().subscribe((data:any) => {this.events = data; console.log(this.events); this.addEvents();});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    headerToolbar:{
        left:'prev,next,today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    dateClick: this.handleDateClick.bind(this),
    events: []
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

}