import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';

@Injectable({
  providedIn: 'root',
})
export class PublicPharmacyProfileService {
  constructor(private http: HttpClient) {}

  subscribeToPromotions(pharmacy: any) {
    return this.http.post<any>(
      'http://localhost:8080/patients/subscribeToPharamacy',
      pharmacy
    );
  }

  getMedicinePharmacy(pharmacyId: Number) {
    return this.http.get<MedicinePharmacy[]>(
      'http://localhost:8080/medicinePharmacy/getMedicineFromPharmacy/' +
        pharmacyId
    );
  }
}
