import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AddMedicineService } from './add-medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  constructor(private fb: FormBuilder, private _addMedicineService: AddMedicineService, private _snackBar: MatSnackBar) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  addMedicineForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  ngOnInit(): void {
    this.addMedicineForm = this.fb.group(
      {
        medicineCode: ['', Validators.required],
        name: ['', Validators.required],
        type: ['', Validators.required],
        form: ['', Validators.required],
        composition: ['', Validators.required],
        manufacturer: ['', Validators.required],
        prescription: [],
        substituteMedicine: [],
        addtionalComments: []
      }
    );
}

  public hasError = (controlName: string, errorName: string) => {
    return this.addMedicineForm.controls[controlName].hasError(errorName);
}

  addMedicine() {
    console.log(this.addMedicineForm.value);
    this._addMedicineService.addMedicine(this.addMedicineForm.value).subscribe( 
    response => {
      this.openSnackBar(response, this.RESPONSE_OK);
      this.addMedicineForm.reset();
    },
    error => {
      this.openSnackBar(error.error, this.RESPONSE_ERROR);
    }
  );
}

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
