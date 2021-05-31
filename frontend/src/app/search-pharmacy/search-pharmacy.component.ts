import {AfterViewInit, Component, ViewChild, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { SearchPharmacyService } from './search-pharmacy.service';
import { AuthService } from '../login/auth.service';


/**
 * @title Data table with sorting, pagination, and filtering.
 */
 @Component({
  selector: 'app-search-pharmacy',
  templateUrl: './search-pharmacy.component.html',
  styleUrls: ['./search-pharmacy.component.css']
})
export class SearchPharmacyComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'address', 'city', 'country', 'rate'];
  dataSource: any;

  pharmacies: any = [];
  firstForm! : FormGroup;
  showTable: boolean = false;
  showForm: boolean = true;
  formInput: any;

  loggedOn = this.authService.getToken() != null ? true : false
  isPatientLoggedOn = this.authService.getTokenData()?.role === 'ROLE_PATIENT' ? true : false;
  isSystemAdminLoggedOn = this.authService.getTokenData()?.role === 'ROLE_SYSTEM_ADMIN' ? true : false;



  

  constructor(private fb: FormBuilder, public service:SearchPharmacyService, private authService: AuthService) {
  }

  ngOnInit(){
   

    this.firstForm = this.fb.group(
      {
        nameOrCity: ['', Validators.required]
      }
    );
  }


  ngAfterViewInit() {
   
  }


  public getPharmacyData(){
    this.formInput = this.firstForm.get('nameOrCity');
    console.log(this.formInput.value);

    this.service.getPharmacies().subscribe((data:any)=>{
      console.log(data)
      this.pharmacies = data.filter((el:{name:any, city:any}) => 
      { if(el.name == this.formInput.value || el.city == this.formInput.value){
        return el
      }else{
        return;
      }
        })
      console.log(this.pharmacies)
      this.dataSource = new MatTableDataSource<any>(this.pharmacies)
    })

    this.showTable = true;
    this.showForm = false;
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  logout() {
    this.authService.logout();
  }
}


