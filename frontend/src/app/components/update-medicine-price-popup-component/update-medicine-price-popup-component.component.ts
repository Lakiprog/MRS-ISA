import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  cost: '';
}
@Component({
  selector: 'app-update-medicine-price-popup-component',
  templateUrl: './update-medicine-price-popup-component.component.html',
  styleUrls: ['./update-medicine-price-popup-component.component.css'],
})
export class UpdateMedicinePricePopupComponentComponent {
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<UpdateMedicinePricePopupComponentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  medicineCostForm = this.formBuilder.group({
    cost: this.data.cost,
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
