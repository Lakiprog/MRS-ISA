import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddMedicineService {

  constructor(private _http: HttpClient) { }

  addMedicine(medicine: any) {
    return this._http.post<any>("http://localhost:8080/medicine/addMedicine", medicine)
               .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
