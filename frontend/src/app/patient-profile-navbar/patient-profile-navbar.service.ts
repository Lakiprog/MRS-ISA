import { Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})


export class PatientProfileNavbarService {
  
  username : string = "";
  
  constructor(private http: HttpClient, private authService: AuthService) {
    if (this.authService.getTokenData()?.username !== "") {
      this.username = this.authService.getTokenData()?.username!;
    }
  }

   getPatient(){
     return this.http.get("http://localhost:8080/patients/searchPatient/" + this.username);
   }
}
