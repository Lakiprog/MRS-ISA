import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-medicine',
  templateUrl: './list-of-medicine.component.html',
  styleUrls: ['./list-of-medicine.component.css'],
})
export class ListOfMedicineComponent implements OnInit {
  medicineList!: Medicine[];
  searchForm: FormGroup;
  medicineId!: String;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      searchValue: [],
    });
  }

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
  updateMedicine(medicine: Medicine): void {
    localStorage.setItem('medicineId', medicine.id);
    this.router.navigate(['updateMedicine']);
  }
  searchMedicineById(): void {
    this.medicineId = this.searchForm.value;
    this.pharmacyAdminService
      .searchMedicineById(this.medicineId)
      .subscribe((res) => {
        console.log(res);
      });
  }
}
