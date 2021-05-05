import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  constructor(private http: HttpClient) { }

  defineCategories(category: any) {
    return this.http.post<any>("http://localhost:8080/loyaltyProgram/defineCategories", category);
  }

  getCategoryNames() {
    return this.http.get<any>("http://localhost:8080/loyaltyProgram/getCategoryNames");
  }

  definePointsForAppointmentAndConsulation(acp: any) {
    return this.http.post<any>("http://localhost:8080/loyaltyProgram/definePointsForAppointmentAndConsulation", acp);
  }
}
