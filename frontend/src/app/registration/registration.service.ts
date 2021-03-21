import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private _http: HttpClient) { }

  register(patient: any) {
    return this._http.post<any>("http://localhost:3030/registration/register", patient)
               .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}