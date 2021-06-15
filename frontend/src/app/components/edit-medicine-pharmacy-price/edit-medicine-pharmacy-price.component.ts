import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/login/auth.service';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrderSupplier } from 'src/app/models/purchase-order-supplier';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Location } from '@angular/common';
import { UpdateMedicinePricePopupComponentComponent } from '../update-medicine-price-popup-component/update-medicine-price-popup-component.component';

@Component({
  selector: 'app-edit-medicine-pharmacy-price',
  templateUrl: './edit-medicine-pharmacy-price.component.html',
  styleUrls: ['./edit-medicine-pharmacy-price.component.css'],
})
export class EditMedicinePharmacyPriceComponent implements OnInit {
  displayedColumnsMedicinePharmacyList: string[] = ['medicine', 'cost', 'edit'];
  medicinePharmacyList!: MedicinePharmacy[];
  pharmacyAdmin!: PharmacyAdmin;
  medicinePharmacyListSource = new MatTableDataSource<any>(
    this.medicinePharmacyList
  );

  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private authService: AuthService,
    public dialog: MatDialog,
    private location: Location
  ) {}
  @ViewChild(MatPaginator)
  paginatorMedicinePharmacy!: MatPaginator;

  ngOnInit(): void {
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
      this.updateLists();
    });
  }

  updateLists() {
    this.pharmacyAdminService
      .getMedicinePharmacyFromPharmacy(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.medicinePharmacyList = res;
        this.medicinePharmacyListSource = new MatTableDataSource<any>(
          this.medicinePharmacyList
        );
        this.medicinePharmacyListSource.paginator =
          this.paginatorMedicinePharmacy;
      });
  }
  updateMedicinePrice(medicinePharmacy: MedicinePharmacy): void {
    const dialogRef = this.dialog.open(
      UpdateMedicinePricePopupComponentComponent,
      {
        width: '300px',
        data: {
          cost: medicinePharmacy.cost,
        },
      }
    );

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      medicinePharmacy.cost = result.value.cost;
      if (result != null) {
        this.pharmacyAdminService
          .editMedicinePharmacyPrice(medicinePharmacy)
          .subscribe((res) => {
            this.updateLists();
          });
      }
    });
  }

  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
