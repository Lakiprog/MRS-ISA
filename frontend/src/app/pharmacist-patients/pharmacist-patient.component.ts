import { Component, OnInit } from '@angular/core';
import {PharmacistPatientService} from './pharmacist-patient.service'

@Component({
  selector: 'app-pharmacist-patients',
  templateUrl: './pharmacist-patient.component.html',
  styleUrls: ['./pharmacist-patient.component.css']
})
export class PharmacistPatientComponent implements OnInit {

  constructor(private service : PharmacistPatientService) { }

  patients = [];

  ngOnInit(): void {
    this.service.getPatientsPharmacist().subscribe((data:any) => {this.patients = data; console.log(this.patients)});
    
  }

}