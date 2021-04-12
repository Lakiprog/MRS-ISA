import { Component, OnInit } from '@angular/core';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-pharmacists',
  templateUrl: './list-of-pharmacists.component.html',
  styleUrls: ['./list-of-pharmacists.component.css'],
})
export class ListOfPharmacistsComponent implements OnInit {
  pharmacists!: Pharmacist[];

  constructor(private pharmacyAdminService: PharmacyAdminService) {}

  ngOnInit(): void {
    this.pharmacyAdminService
      .getAllPharmacists()
      .subscribe((res) => (this.pharmacists = res));
  }

  deletePharmacist(pharmacist: Pharmacist): void {
    this.pharmacyAdminService.deletePharmacist(pharmacist).subscribe((res) => {
      this;
      this.ngOnInit();
    });
  }
}
