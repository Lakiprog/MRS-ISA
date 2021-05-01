import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DermatologistAppointmentInfoService {

  constructor(private _http: HttpClient) { }

  setDone(appointment:any){
    return this._http.put("http://localhost:8080/appointment_creation/setDoneDermatologist", appointment);
  }

  getMeds(id:number, start:string){
    return this._http.get("http://localhost:8080/medicinePharmacy/getMedicineDermatologist/pharmacy="+id+"start="+start);
  }
}