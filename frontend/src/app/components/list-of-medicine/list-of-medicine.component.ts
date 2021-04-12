import { Component, OnInit } from '@angular/core';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-medicine',
  templateUrl: './list-of-medicine.component.html',
  styleUrls: ['./list-of-medicine.component.css'],
})
export class ListOfMedicineComponent implements OnInit {
  medicineList!: Medicine[];

  constructor(private pharmacyAdminService: PharmacyAdminService) {}

  ngOnInit(): void {
    this.pharmacyAdminService
      .getAllMedicine()
      .subscribe((res) => (this.medicineList = res));
  }

  deleteMedicine(medicine: Medicine): void {
    this.pharmacyAdminService.deleteMedicine(medicine).subscribe((res) => {
      this.ngOnInit();
    });
  }
}
