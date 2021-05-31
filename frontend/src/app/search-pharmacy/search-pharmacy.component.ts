import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { SearchPharmacyService } from './search-pharmacy.service';
import { Pharmacy } from '../user-complaint/user-complaint.component';
import { PharmacyService } from '../services/pharmacy.service';
import { Router } from '@angular/router';

/**
 * @title Data table with sorting, pagination, and filtering.
 */
@Component({
  selector: 'app-search-pharmacy',
  templateUrl: './search-pharmacy.component.html',
  styleUrls: ['./search-pharmacy.component.css'],
})
export class SearchPharmacyComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'address', 'city', 'country', 'rate'];
  dataSource!: MatTableDataSource<any>;
  displayedColumnsPharmacyList: string[] = ['name', 'actions'];
  pharmacyList!: Pharmacy[];
  pharmacyListSource = new MatTableDataSource<any>(this.pharmacyList);
  pharmacies: any = [];
  firstForm!: FormGroup;
  showTable: boolean = false;
  showForm: boolean = true;
  formInput: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private fb: FormBuilder,
    public service: SearchPharmacyService,
    public pharmacyService: PharmacyService,
    private router: Router
  ) {}

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.pharmacies);

    this.firstForm = this.fb.group({
      nameOrCity: ['', Validators.required],
    });
    this.pharmacyService.getAllPharmacies().subscribe((data) => {
      this.pharmacyList = data;
      this.pharmacyListSource = new MatTableDataSource<any>(this.pharmacyList);
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  public getPharmacyData() {
    this.formInput = this.firstForm.get('nameOrCity');
    this.service.getPharmacies().subscribe((data: any) => {
      this.pharmacies = data;
      this.dataSource.data = this.pharmacies;
      this.dataSource.filter = this.formInput.value.trim().toLowerCase();
      console.log(this.dataSource);
    });

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

  goToPharmacy(pharmacy: Pharmacy) {
    localStorage.setItem('pharmacy', JSON.stringify(pharmacy));
    this.router.navigate(['/publicPharmacyProfilePage']);
  }
}
