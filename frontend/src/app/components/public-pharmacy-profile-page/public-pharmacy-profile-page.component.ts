import { Component, OnInit, ViewChild } from '@angular/core';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { PublicPharmacyProfileService } from './public-pharmacy-profile.service';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-public-pharmacy-profile-page',
  templateUrl: './public-pharmacy-profile-page.component.html',
  styleUrls: ['./public-pharmacy-profile-page.component.css'],
})
export class PublicPharmacyProfilePageComponent implements OnInit {
  displayedColumnsMedicinePharmacyList: string[] = [
    'medicine',
    'amount',
    'cost',
  ];
  pharmacy!: Pharmacy;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  medicinePharmacyList!: MedicinePharmacy[];
  medicinePharmacyListSource = new MatTableDataSource<any>(
    this.medicinePharmacyList
  );
  loggedOn = this.authService.getToken() != null ? true : false;

  constructor(
    private location: Location,
    private authService: AuthService,
    private publicPharmacyProfilePageService: PublicPharmacyProfileService,
    private _snackBar: MatSnackBar
  ) {}

  @ViewChild(MatPaginator)
  paginatorMedicinePharmacy!: MatPaginator;
  ngOnInit(): void {
    this.pharmacy = JSON.parse(localStorage.getItem('pharmacy') || '{}');
    console.log(this.pharmacy);
    this.publicPharmacyProfilePageService
      .getMedicinePharmacy(this.pharmacy.id)
      .subscribe((result) => {
        this.medicinePharmacyList = result;
        this.medicinePharmacyListSource = new MatTableDataSource<any>(
          this.medicinePharmacyList
        );
        this.medicinePharmacyListSource.paginator =
          this.paginatorMedicinePharmacy;
      });
    //   TODO
    //   this.publicPharmacyProfilePageService.getDermatologistAppointments().subscribe(
    //   result => {
    //   this.dermatologistAppointmentList = result });
    //   this.publicPharmacyProfilePageService.getPharmacyEmployees().subscribe(
    //   result => {
    //   this.employeeList = result });
  }

  subscribeToPromotions() {
    this.publicPharmacyProfilePageService
      .subscribeToPromotions(this.pharmacy)
      .subscribe((response) => {
        this.openSnackBar(response, this.RESPONSE_OK);
      });
  }

  back(): void {
    localStorage.removeItem('pharmacy');
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
}
