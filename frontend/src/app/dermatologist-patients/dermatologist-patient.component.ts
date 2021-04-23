import { Component, OnInit } from '@angular/core';
import {DermatologistPatientService} from './dermatologist-patient.service';
import {Sort} from '@angular/material/sort';

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

  sortData(sort: Sort){
    this.patients = this.patients.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a['name'], b['name'], isAsc);
        case 'id': return this.compare(a['id'], b['id'], isAsc);
        case 'username': return this.compare(a['username'], b['username'], isAsc);
        case 'surname': return this.compare(a['surname'], b['surname'], isAsc);
        case 'email': return this.compare(a['email'], b['email'], isAsc);
        case 'adress': return this.compare(a['adress'], b['adress'], isAsc);
        case 'city': return this.compare(a['city'], b['city'], isAsc);
        case 'country': return this.compare(a['country'], b['country'], isAsc);
        case 'phoneNumber': return this.compare(a['phoneNumber'], b['phoneNumber'], isAsc);
        default: return 0;
    }
  });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

}