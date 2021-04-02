import { Component, OnInit } from '@angular/core';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-pharmacy-admin',
  templateUrl: './pharmacy-admin.component.html',
  styleUrls: ['./pharmacy-admin.component.css'],
})
export class PharmacyAdminComponent implements OnInit {
  pharmacists: Pharmacist[] = [];
  constructor(private pharmacyAdminService: PharmacyAdminService) {}

  ngOnInit(): void {}
}
