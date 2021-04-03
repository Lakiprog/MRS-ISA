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

  events = [];

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
    this.calendarOptions.events = this.events;
 }

}