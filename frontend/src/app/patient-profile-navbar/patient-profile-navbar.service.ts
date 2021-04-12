import { Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})


export class PatientProfileNavbarService {
  
  private readonly patientURL = 'http://localhost:8080/patients//searchPatient/kek';

  constructor(private http: HttpClient) {}

   public getPatient(){
     return this.http.get(this.patientURL);
   }
}
