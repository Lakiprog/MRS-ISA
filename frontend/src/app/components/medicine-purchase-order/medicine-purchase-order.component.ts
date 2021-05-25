import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, Inject } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PurchaseOrderMedicine } from 'src/app/models/purchase-order-medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Pharmacist } from 'src/app/user-complaint/user-complaint.component';
import { AddMedicineToCartPopupComponent } from '../add-medicine-to-cart-popup/add-medicine-to-cart-popup.component';
import { stringify } from '@angular/compiler/src/util';
import { SubmitPurchaseOrderPopupComponent } from '../submit-purchase-order-popup/submit-purchase-order-popup.component';

export interface DialogData {
  quantity: Number;
}

@Component({
  selector: 'app-medicine-purchase-order',
  templateUrl: './medicine-purchase-order.component.html',
  styleUrls: ['./medicine-purchase-order.component.css'],
})
export class MedicinePurchaseOrderComponent implements OnInit {
  quantity!: Number;
  medicineList!: Medicine[];
  purchaseOrderMedicineForm: FormGroup;
  pharmacy!: Pharmacist;
  pharmacyAdmin!: PharmacyAdmin;
  data!: DummyModel;
  purchaseOrderMedicine: PurchaseOrderMedicine = {
    quantity: 0,
    medicine: null as any,
  };
  purchaseOrder: PurchaseOrder = {
    pharmacy: null as any,
    purchaseOrderName: null as any,
    purchaseOrderMedicine: [],
    purchaseOrderSupplier: null as any,
  };
  constructor(
    public dialog: MatDialog,
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.purchaseOrderMedicineForm = this.formBuilder.group({
      purchaseOrderName: [],
    });
  }

  ngOnInit(): void {
    this.purchaseOrderMedicineForm = this.formBuilder.group({
      purchaseOrderName: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
      this.pharmacyAdminService
        .getMedicineFromPharmacy(this.pharmacyAdmin.pharmacy)
        .subscribe((res) => (this.medicineList = res));
    });
  }

  openDialog(medicine: Medicine): void {
    let purchaseOrderMedicine: PurchaseOrderMedicine = {
      quantity: 0,
      medicine: null as any,
    };
    purchaseOrderMedicine.medicine = medicine;

    const dialogRef = this.dialog.open(AddMedicineToCartPopupComponent, {
      width: '250px',
      data: {
        medicineName: medicine.name,
        quantity: this.quantity,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      purchaseOrderMedicine.quantity = result;
      if (result != null) {
        this.purchaseOrder.purchaseOrderMedicine.push(purchaseOrderMedicine);
      }
    });
  }
  createPurchaseOrder() {
    this.purchaseOrder.pharmacy = this.pharmacyAdmin.pharmacy;

    const dialogRef = this.dialog.open(SubmitPurchaseOrderPopupComponent, {
      width: '250px',
      data: {
        purchaseOrderName: this.purchaseOrder.purchaseOrderName,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.purchaseOrder.purchaseOrderName = result;
      if (result != null) {
        this.pharmacyAdminService
          .createPurchaseOrder(this.purchaseOrder)
          .subscribe((res) => {
            localStorage.removeItem('pharmacist');
            this.router.navigate(['/pharmacyProfilePage']);
          });
      }
    });
  }
}
