import { Component, OnInit } from '@angular/core';
import {PharmacistCalendarService} from './pharmacist-calendar.service'
import { CalendarOptions } from '@fullcalendar/angular';

@Component({
  selector: 'app-pharmacist-calendar',
  templateUrl: './pharmacist-calendar.component.html',
  styleUrls: ['./pharmacist-calendar.component.css']
})
export class PharmacistCalendarComponent implements OnInit {

  constructor(private service : PharmacistCalendarService) { }

  events = [];

  ngOnInit(): void {
    this.service.getAppointmentsPharmacist().subscribe((data:any) => {this.events = data; console.log(this.events); this.addEvents();});
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