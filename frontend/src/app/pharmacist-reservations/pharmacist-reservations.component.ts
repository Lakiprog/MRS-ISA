import { Component, OnInit, ViewChild } from '@angular/core';
import {PharmacistReservationsService} from './pharmacist-reservations.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-pharmacist-reservations',
  templateUrl: './pharmacist-reservations.component.html',
  styleUrls: ['./pharmacist-reservations.component.css']
})
export class PharmacistReservationsComponent implements OnInit {

  constructor(private service : PharmacistReservationsService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar) { }

  @ViewChild(MatPaginator)
  paginatorMedicineSupply!: MatPaginator;

  medicinePrescribedForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['name', 'quantity'];
  displayedColumnsMedicinePrescribed: string[] = ['name', 'cost'];
  medicineSupplyData:any = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  medicinePrescribedData:any = [];
  medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
  reservation:any;

  ngOnInit(): void {
      this.medicinePrescribedForm = this.fb.group(
      {
        reservationSearch: ['']
      });
    
    }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
  }

   getReservation(){
    this.service.getReservations(this.medicinePrescribedForm.get('reservationSearch')?.value).subscribe(
      response => {
        
        if(response.length != 0){
            this.medicineSupplyData = response[0];
            this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
            this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;

            this.medicinePrescribedData = response[1];
            this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
            this.reservation = response[1][0]
        }else{
            this.openSnackBar("There is no reservation with that id in your pharmacy, or the patient hasn't picked it up in time", this.RESPONSE_ERROR);
        }

      }
    )
   }

   openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  giveOut(){
    if(this.reservation){
        this.service.giveOut(this.reservation).subscribe(
            response => {
              this.openSnackBar(response, this.RESPONSE_OK);
              this.reset();
            },
            error => {
              this.openSnackBar(error.error, this.RESPONSE_ERROR);
            }
          );
    }else{
        this.openSnackBar("You haven't picked a reservation, please do so.", this.RESPONSE_ERROR);
    }
  }

  reset(){
    this.medicinePrescribedForm.reset();
    this.medicineSupplyData = [];
    this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;

    this.medicinePrescribedData = [];
    this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
    this.reservation = undefined;
  }

}