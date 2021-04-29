import { Component, OnInit } from '@angular/core';
import {PharmacistPatientService} from './pharmacist-patient.service'
import {Sort} from '@angular/material/sort';

@Component({
  selector: 'app-pharmacist-patients',
  templateUrl: './pharmacist-patient.component.html',
  styleUrls: ['./pharmacist-patient.component.css']
})
export class PharmacistPatientComponent implements OnInit {

  constructor(private service : PharmacistPatientService) { }

  patients = [];

  ngOnInit(): void {
    this.service.getPatientsPharmacist().subscribe((data:any) => {this.patients = data;});
    
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
        case 'address': return this.compare(a['address'], b['address'], isAsc);
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