import { Component, OnInit, ViewChild, ɵɵsetComponentScope } from '@angular/core';
import {PharmacistAppointmentInfoService} from './pharmacist-appointment-info.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  @ViewChild(MatPaginator)
  paginatorMedicinePrescribed!: MatPaginator;
  
  updateForm! : FormGroup;
  medicineForm! : FormGroup;
  medicinePrescribedForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['name', 'composition', 'quantity', 'add'];
  displayedColumnsMedicinePrescribed: string[] = ['name', 'therapy', 'quantity', 'remove'];
  medicineSupplyData:any = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  medicinePrescribedData:any = [];
  medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
  information = {};
  appointment:any = {};

  ngOnInit(): void {
    this.medicineForm = this.fb.group(
      {
        medicationSearch: ['']
      }
    );
    this.medicinePrescribedForm = this.fb.group(
      {
        therapy: ['', Validators.required],
        quantity: ['', Validators.required],
        tableRows: this.fb.array([])
      }
    );
    this.updateForm = this.fb.group(
        {
          comments: [''],
          medication: ['']
        }
    );
    let data:any = history.state.data;
    this.information = data.information;
    this.appointment = data.appointment;
    this.updateForm.get('comments')?.setValue(data.information.comment);
    
    if(data.information.medication){
      data.information.medication.forEach((element:any) => {
        this.tableRows.push(this.fb.group({
          name: [element.name],
          medicationTherapy: [element.medicationTherapy],
          medicationQuantity: [element.medicationQuantity],
          medicine: [element.medicine]
        }));
      });
      this.medicinePrescribedData = data.information.medication;
      this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
    }
    
    this.getMeds();
  }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
    this.medicinePrescribedDataSource.paginator = this.paginatorMedicinePrescribed;
  }

  get getFormControls() {
    return this.medicinePrescribedForm.get('tableRows') as FormArray;
  }

  get tableRows(){
    return this.medicinePrescribedForm.get('tableRows') as FormArray;
  }

  get inputs(){
    return this.medicinePrescribedForm.controls;
  }


   newAppointment(){
    this.medicinePrescribedData= [];
    this.tableRows.value.forEach((element:any) => {
      this.medicinePrescribedData.push({name: element.name, medicationTherapy: element.medicationTherapy, medicationQuantity: element.medicationQuantity, medicine:element.medicine})
    });
    this.router.navigate(['/PharmacistAppointmentCreationComponent'], {state: {data: {appointment : this.appointment, information : {comment: this.updateForm.get('comments')?.value, medication: this.medicinePrescribedData}}}});
   }

   endAppointment(){
      let meds: any = []
      this.tableRows.value.forEach((element:any) => {
        meds.push({"medicine": element.medicine, "therapy": element.medicationTherapy, "quantity": element.medicationQuantity})
      });
      this.service.endAppointment(this.appointment, meds, this.updateForm.get('comments')?.value).subscribe(response => {
      this.openSnackBar(response, this.RESPONSE_OK);
      this.router.navigate(['/PharmacistHomePage']);
    },
    error => {
      this.openSnackBar(error.error, this.RESPONSE_ERROR);
    })
   }
   
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

  addMedicine(medicine:any, quantity:number){
    this.service.getSubs(this.appointment.pharmacy.id, medicine.id, this.appointment.patient.id).
    subscribe(subs=>{
      if(subs.length == 0){
        this.addToTable(medicine);
      }else{
        if(quantity == 0){
          this.openSnackBar("There is no medicine of this kind in the pharmacy, the admin has been notified. If you wish to prescribe a substitute, we have searched them for you already.", this.RESPONSE_ERROR);
        }else{
          this.openSnackBar("The patient is allergic to this medication. If you wish to prescribe a substitute, we have searched them for you already.", this.RESPONSE_ERROR);
        }
        if(subs[0].medicine.name != "ERROR"){
          this.medicineSupplyData = subs;
          this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
        }else{
          this.medicineSupplyData = [];
          this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
        }
      }
    });
    
  }

  addToTable(medicine:any){
    let found = false;
    this.tableRows.value.forEach((element:any) => {
      if(element.name == medicine.name){
        
        this.medicineSupplyData = this.medicineSupplyData.filter((med:any) => med.medicine.name !== medicine.name);
        this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);

        found = true;
        return;
      }
    });

    if(found){
      return;
    }

    this.tableRows.push(this.fb.group({
      name: [medicine.name],
      medicationTherapy: ['1'],
      medicationQuantity: ['1'],
      medicine : [medicine]
    }));

    this.medicinePrescribedData.push(medicine);
    this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
    
    this.medicineSupplyData = this.medicineSupplyData.filter((med:any) => med.medicine.name !== medicine.name);
    this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  }

  removeMedicine(medicine:any){
    let index = (<FormArray>this.medicinePrescribedForm.get('tableRows')).controls.findIndex(x => x.value.Name === medicine.name);
    const control = <FormArray>this.medicinePrescribedForm.controls['tableRows'];
    control.removeAt(index);

    this.medicinePrescribedData = this.medicinePrescribedData.filter((med:any) => med.name !== medicine.name);
    this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
  }

}