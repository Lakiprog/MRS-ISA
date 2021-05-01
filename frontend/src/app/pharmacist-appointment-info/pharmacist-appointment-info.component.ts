import { Component, OnInit, ViewChild } from '@angular/core';
import {PharmacistAppointmentInfoService} from './pharmacist-appointment-info.service'
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-pharmacist-appointment-info',
  templateUrl: './pharmacist-appointment-info.component.html',
  styleUrls: ['./pharmacist-appointment-info.component.css']
})
export class PharmacistAppointmentInfoComponent implements OnInit {

  constructor(private service : PharmacistAppointmentInfoService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar) { }

  @ViewChild(MatPaginator)
  paginatorMedicineSupply!: MatPaginator;
  
  updateForm! : FormGroup;
  medicineForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['name', 'composition', 'quantity', 'add'];
  medicineSupplyData:any = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  information = {};
  appointment:any = {};

  ngOnInit(): void {
    this.medicineForm = this.fb.group(
      {
        medicationSearch: ['']
      }
    );
    this.updateForm = this.fb.group(
        {
          comments: [''],
          medication: ['']
        }
    );
    let data = history.state.data;
    this.information = data.information;
    this.appointment = data.appointment;
    this.updateForm.get('comments')?.setValue(data.information.comment);
  }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
  }

   newAppointment(){
    this.router.navigate(['/PharmacistAppointmentCreationComponent'], {state: {data: {appointment : this.appointment, information : {comment: this.updateForm.get('comments')?.value, medication:[]}}}});
   }

   endAppointment(){}
   
   endAppointmentNoPatient(){
       this.service.setDone(this.appointment).subscribe(()=>this.router.navigate(['/PharmacistHomePage']));
   }

   getMeds(){
    this.service.getMeds(this.appointment.pharmacy.id, this.medicineForm.get('medicationSearch')?.value).subscribe(
      response => {
        this.medicineSupplyData = response;
        this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
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

  addMedicine(){

  }

}