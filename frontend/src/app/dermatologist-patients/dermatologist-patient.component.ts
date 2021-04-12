import { Component, OnInit } from '@angular/core';
import {DermatologistPatientService} from './dermatologist-patient.service'

@Component({
  selector: 'app-dermatologist-patients',
  templateUrl: './dermatologist-patient.component.html',
  styleUrls: ['./dermatologist-patient.component.css']
})
export class DermatologistPatientComponent implements OnInit {

  constructor(private service : DermatologistPatientService) { }

  patients = [];

  ngOnInit(): void {
    this.service.getPatientsDermatologist().subscribe((data:any) => {this.patients = data; console.log(this.patients)});
    
  }

}