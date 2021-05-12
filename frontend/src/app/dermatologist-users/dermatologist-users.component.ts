import { Component, OnInit, ViewChild } from '@angular/core';
import {DermatologistUsersService} from './dermatologist-users.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';;
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-dermatologist-users',
  templateUrl: './dermatologist-users.component.html',
  styleUrls: ['./dermatologist-users.component.css']
})
export class DermatologistUsersComponent implements OnInit {

  constructor(private service : DermatologistUsersService, private router: Router, private fb: FormBuilder) { }

  @ViewChild(MatPaginator)
  paginatorPatients!: MatPaginator;

  patientsForm! : FormGroup;
  displayedColumnsPatients: string[] = ['name', 'surname', 'username', 'email', 'adress', 'city', 'country', 'phoneNumber'];
  patientsData:any = [];
  patientsDataSource = new MatTableDataSource<any>(this.patientsData);

  ngOnInit(): void {
      this.patientsForm = this.fb.group(
      {
        nameSearch: [''],
        surnameSearch: ['']
      });
      this.getPatients("", "");
    }

  ngAfterViewInit(): void {
    this.patientsDataSource.paginator = this.paginatorPatients;
  }

   getPatients(name:string, surname:string){
    this.service.getPatients(name, surname).subscribe(
      response =>{
        this.patientsData = response;
        this.patientsDataSource = new MatTableDataSource<any>(this.patientsData);
        this.patientsDataSource.paginator = this.paginatorPatients;
      }
    );
   }

  filter(){
    this.getPatients(this.patientsForm.get("nameSearch")?.value, this.patientsForm.get("surnameSearch")?.value);
  }

  back(){
    this.router.navigate(['/DermatologistHomePage']);
  }
}