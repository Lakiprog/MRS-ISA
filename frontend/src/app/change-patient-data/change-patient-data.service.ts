import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChangePatientDataService {


  
  private readonly changeDataURL = 'http://localhost:8080/patients/changeData/kek';
  private readonly getChangeDataURL = 'http://localhost:8080/patients/changeDataShow/kek';
  

  constructor(private http: HttpClient) {

  }

  
  public dataShow(){
    return this.http.get(this.getChangeDataURL);
  }

  public changeData(data:any){
      return this.http.put<any>(this.changeDataURL, data).pipe(catchError(this.errorHandler));
  }


  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
