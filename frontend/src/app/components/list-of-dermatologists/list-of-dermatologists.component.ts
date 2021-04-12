import { Component, OnInit } from '@angular/core';
import { Dermatologist } from 'src/app/models/dermatologist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-dermatologists',
  templateUrl: './list-of-dermatologists.component.html',
  styleUrls: ['./list-of-dermatologists.component.css'],
})
export class ListOfDermatologistsComponent implements OnInit {
  dermatologists!: Dermatologist[];

  constructor(private pharmacyAdminService: PharmacyAdminService) {}

  ngOnInit(): void {
    this.pharmacyAdminService
      .getAllDermatologists()
      .subscribe((res) => (this.dermatologists = res));
  }

  deleteDermatologist(dermatologist: Dermatologist): void {
    this.pharmacyAdminService
      .deleteDermatologist(dermatologist)
      .subscribe((res) => {
        this;
        this.ngOnInit();
      });
  }
}
