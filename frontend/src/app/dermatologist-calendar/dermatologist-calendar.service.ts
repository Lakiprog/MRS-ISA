import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DermatologistCalendarService {

  constructor(private _http: HttpClient) { }

    public getAppointmentsDermatologist(){

        return this._http.get("http://localhost:8080/calendar/calendarDermatologist/6")
    }
}