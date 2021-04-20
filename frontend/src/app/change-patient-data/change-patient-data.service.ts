import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ChangePatientDataService {


  username : string = "";

  constructor(private http: HttpClient, private authService: AuthService) {
    if (this.authService.getTokenData()?.username !== "") {
      this.username = this.authService.getTokenData()?.username!;
    }
  }

  dataShow(){
    return this.http.get("http://localhost:8080/patients/changeDataShow/" + this.username);
  }

  changeData(data:any){
      return this.http.put<any>("http://localhost:8080/patients/changeData/" + this.username, data).pipe(catchError(this.errorHandler));
  }


  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
