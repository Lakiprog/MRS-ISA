import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Dermatologist } from 'src/app/models/dermatologist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-add-new-dermatologist',
  templateUrl: './add-new-dermatologist.component.html',
  styleUrls: ['./add-new-dermatologist.component.css'],
})
export class AddNewDermatologistComponent implements OnInit {
  dermatologistForm: FormGroup;
  dermatologist!: Dermatologist;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.dermatologistForm = this.formBuilder.group({
      name: [],
      surname: [],
      email: [],
      username: [],
      password: [],
    });
  }

  ngOnInit(): void {
    this.dermatologistForm = this.formBuilder.group({
      id: [],
      name: [],
      surname: [],
      email: [],
      username: [],
      password: [],
    });
  }

  addNewDermatologist() {
    var dermatologist = this.dermatologistForm.value;
    this.pharmacyAdminService
      .createDermatologist(dermatologist)
      .subscribe((res) => {
        this.dermatologist = res;
      });
    this.router.navigate(['/pharmacyAdmin']);
  }
}
