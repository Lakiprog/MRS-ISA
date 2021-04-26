import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DermatologistPatientService {

  constructor(private _http: HttpClient) { }

    public getPatientsDermatologist(){

        return this._http.get("http://localhost:8080/patient-search/searchPatientsDermatologist")
    }
}