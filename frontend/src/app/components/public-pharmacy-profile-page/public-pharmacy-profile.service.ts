import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PublicPharmacyProfileService {

  constructor(private http: HttpClient) { }

  subscribeToPromotions(pharmacy: any) {
    return this.http.post<any>("http://localhost:8080/patients/subscribeToPharamacy", pharmacy);
  }
}
