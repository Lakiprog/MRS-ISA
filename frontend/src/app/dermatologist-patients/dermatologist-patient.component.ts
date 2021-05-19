import { Component, OnInit } from '@angular/core';
import {DermatologistPatientService} from './dermatologist-patient.service';
import {Sort} from '@angular/material/sort';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dermatologist-patients',
  templateUrl: './dermatologist-patient.component.html',
  styleUrls: ['./dermatologist-patient.component.css']
})
export class DermatologistPatientComponent implements OnInit {

  constructor(private service : DermatologistPatientService, private router: Router, private fb: FormBuilder) { }

  patientForm! :FormGroup;
  patients:any = [];

  ngOnInit(): void {
    this.service.getPatientsDermatologist("").subscribe((data:any) => {
      this.patients = data;
      this.patients.forEach((element:any) => {
        element.date = new Date(element.date[0], element.date[1]-1, element.date[2], element.date[3]+1, element.date[4]);
      });
    });
    this.patientForm = this.fb.group(
      {
        patientSearch: ['']
      }
    );
  }

  sortData(sort: Sort){
    this.patients = this.patients.sort((a:any, b:any) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a['patient']['name'], b['patient']['name'], isAsc);
        case 'id': return this.compare(a['patient']['id'], b['patient']['id'], isAsc);
        case 'username': return this.compare(a['patient']['username'], b['patient']['username'], isAsc);
        case 'surname': return this.compare(a['patient']['surname'], b['patient']['surname'], isAsc);
        case 'email': return this.compare(a['patient']['email'], b['patient']['email'], isAsc);
        case 'address': return this.compare(a['patient']['address'], b['patient']['address'], isAsc);
        case 'city': return this.compare(a['patient']['city'], b['patient']['city'], isAsc);
        case 'country': return this.compare(a['patient']['country'], b['patient']['country'], isAsc);
        case 'phoneNumber': return this.compare(a['patient']['phoneNumber'], b['patient']['phoneNumber'], isAsc);
        case 'date': return this.compare(a['date'], b['date'], isAsc);
        default: return 0;
    }
  });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  getPatients(){
    this.service.getPatientsDermatologist(this.patientForm.get('patientSearch')?.value).subscribe((data:any) => {
      this.patients = data;
      this.patients.forEach((element:any) => {
        element.date = new Date(element.date[0], element.date[1]-1, element.date[2], element.date[3]+1, element.date[4]);
      });
    });
  }

  back(){
    this.router.navigate(['/DermatologistHomePage']);
  }

}