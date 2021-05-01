import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PharmacistAppointmentInfoService {

  constructor(private _http: HttpClient) { }

  setDone(appointment:any){
    return this._http.put("http://localhost:8080/appointment_creation/setDonePharmacist", appointment);
  }

  getMeds(id:number, start:string){
    return this._http.get("http://localhost:8080/medicinePharmacy/getMedicinePharmacist/pharmacy="+id+"start="+start);
  }
}