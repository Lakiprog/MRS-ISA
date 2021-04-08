import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchPharmacyService {
  private readonly getPharmaciesURL = 'http://localhost:8080/pharmacy/getAll';

  constructor(private http: HttpClient) {}


  
  public getPharmacies(){
    return this.http.get(this.getPharmaciesURL);
  }

}
