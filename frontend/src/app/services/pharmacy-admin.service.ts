import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pharmacist } from '../models/pharmacist';
import { Medicine } from '../models/medicine';
import { Dermatologist } from '../models/dermatologist';

@Injectable({
  providedIn: 'root',
})
export class PharmacyAdminService {
  private pharmacistsUrl!: string;
  private medicineUrl!: string;
  private dermatologistUrl!: string;

  constructor(private httpClient: HttpClient) {
    this.pharmacistsUrl = 'http://localhost:8080/pharmacist/all';
    this.medicineUrl = 'http://localhost:8080/medicine/all';
    this.dermatologistUrl = 'http://localhost:8080/dermatologist/all';
  }

  public deletePharmacist(pharmacist: Pharmacist) {
    return this.httpClient.delete<Pharmacist>(
      'http://localhost:8080/pharmacist/' + pharmacist.id + '/delete'
    );
  }

  public getAllPharmacists(): Observable<Pharmacist[]> {
    return this.httpClient.get<Pharmacist[]>(this.pharmacistsUrl);
  }

  public createPharmacist(pharmacist: Pharmacist) {
    return this.httpClient.post<Pharmacist>(
      'http://localhost:8080/pharmacist/add',
      pharmacist
    );
  }

  public deleteMedicine(medicine: Medicine) {
    return this.httpClient.delete<Medicine>(
      'http://localhost:8080/medicine/' + medicine.id + '/delete'
    );
  }

  public getAllMedicine(): Observable<Medicine[]> {
    return this.httpClient.get<Medicine[]>(this.medicineUrl);
  }

  public createMedicine(medicine: Medicine) {
    return this.httpClient.post<Medicine>(
      'http://localhost:8080/medicine/addMedicine',
      medicine
    );
  }

  public deleteDermatologist(dermatologist: Dermatologist) {
    return this.httpClient.delete<Dermatologist>(
      'http://localhost:8080/dermatologist/' + dermatologist.id + '/delete'
    );
  }

  public getAllDermatologists(): Observable<Dermatologist[]> {
    return this.httpClient.get<Dermatologist[]>(this.dermatologistUrl);
  }

  public createDermatologist(dermatologist: Dermatologist) {
    return this.httpClient.post<Dermatologist>(
      'http://localhost:8080/dermatologist/add',
      dermatologist
    );
  }
}
