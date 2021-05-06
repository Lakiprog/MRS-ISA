import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PatientSchedDermaAppService {

  private readonly getAppURL = 'http://localhost:8080/make_dermatologist_appointment/getPatientDerApp';

  constructor(private http: HttpClient) { }

  public getAllDerApp(){
    return this.http.get(this.getAppURL);
  }
}
