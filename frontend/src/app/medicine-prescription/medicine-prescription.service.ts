import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MedicinePrescriptionService {

  constructor(private http: HttpClient) { }

  sendQrCode(qrCode: any) {
    return this.http.post<any>("http://localhost:8080/patients/sendQrCode", qrCode)
  }
}
