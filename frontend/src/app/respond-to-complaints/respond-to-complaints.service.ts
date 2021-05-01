import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RespondToComplaintsService {

  constructor(private http: HttpClient) { }

  getComplaintsToRespond() {
    return this.http.get<any>("http://localhost:8080/complaint/getComplaintsToRespond");
  }

  getResponses() {
    return this.http.get<any>("http://localhost:8080/complaint/getResponses");
  }

  sendResponse(response: any) {
    return this.http.put<any>("http://localhost:8080/complaint/sendResponse", response);
  }
}