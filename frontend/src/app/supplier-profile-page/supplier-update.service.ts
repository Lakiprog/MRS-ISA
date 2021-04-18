import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SupplierUpdateService {

  username : string = "";

  constructor(private _http: HttpClient, private authService: AuthService) 
  { 
    if (this.authService.getTokenData()?.username !== "") {
      this.username = this.authService.getTokenData()?.username!;
    }
  }

  getSupplierData() {
    return this._http.get<any>("http://localhost:8080/suppliers/getSupplierData/" + this.username);
  }

  updateSupplierData(supplier: any) {
    return this._http.put<any>("http://localhost:8080/suppliers/updateSupplierData", supplier)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
