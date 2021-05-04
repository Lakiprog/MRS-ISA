import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { SearchFilterMedicineService } from './search-filter-medicine.service';
import jspdf from 'jspdf';

@Component({
  selector: 'app-search-filter-medicine',
  templateUrl: './search-filter-medicine.component.html',
  styleUrls: ['./search-filter-medicine.component.css']
})
export class SearchFilterMedicineComponent implements OnInit, AfterViewInit{

  displayedColumnsMedicine: string[] = ['name', 'medicineType', 'averageRating', 
    'viewMedicineDetails', 'pharmacy', 'price', 'quantity'];
  medicineTypes = [];
  grades = ['1', '2', '3', '4', '5'];
  medicineData = [];
  searchResults = [];
  medicineDataSource = new MatTableDataSource<any>(this.medicineData);
  searchAndFilterForm!: FormGroup;
  loggedOn = this.authService.getToken() != null ? true : false
  selectedMedicine: any;
  isPatientLoggedOn = this.authService.getTokenData()?.role === 'ROLE_PATIENT' ? true : false;

  constructor
  (
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private searchFilterMedicineService: SearchFilterMedicineService,
  ) {}

  @ViewChild(MatPaginator)
  paginatorMedicine!: MatPaginator;

  ngOnInit(): void {
    this.getMedicineTypes();
    this.getAllMedicinePharmacy();
    this.searchAndFilterForm = this.fb.group(
      {
        medicineType: [''],
        averageRating: [''],
        search: ['']
      }
    ); 
  }
  ngAfterViewInit(): void {
    this.medicineDataSource.paginator = this.paginatorMedicine;
  }

  get search() {
    return this.searchAndFilterForm.get('search')?.value
  }

  getMedicineTypes() {
    this.searchFilterMedicineService.getMedicineTypes().subscribe(
      data => {
        this.medicineTypes = data;
      }
    )
  }

  getAllMedicinePharmacy() {
    this.searchFilterMedicineService.getAllMedicinePharmacy().subscribe(
      data => {
        this.medicineData = data;
        this.medicineDataSource = new MatTableDataSource<any>(this.medicineData);
        this.medicineDataSource.paginator = this.paginatorMedicine;
        this.medicineDataSource.filterPredicate = (data1, filter: string)  => {
          const accumulator = (currentTerm:any, key:any) => {
              return currentTerm + data1.medicine.medicineType + data1.medicine.averageRating
          };
          const dataStr = Object.keys(data1).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };
      }
    );
  }

  searchMedicineByName() {
    this.searchFilterMedicineService.searchMedicineByName(this.search).subscribe(
      data => {
        this.searchResults = data;
        this.medicineDataSource = new MatTableDataSource<any>(this.searchResults);
        this.medicineDataSource.paginator = this.paginatorMedicine;
        this.medicineDataSource.filterPredicate = (data1, filter: string)  => {
          const accumulator = (currentTerm:any, key:any) => {
              return currentTerm + data1.medicine.medicineType + data1.medicine.averageRating
          };
          const dataStr = Object.keys(data1).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };
      }
    )
  }

  applyFilter(option: string) {
      const filterValue = option;
      this.medicineDataSource.filter = filterValue;
  }

  clearSearchAndFilter() {
    this.medicineDataSource.filter = '';
    this.searchAndFilterForm.reset();
    this.getAllMedicinePharmacy();
  }

  viewDetails(medicine: any) {
    const pdfDocument = new jspdf();
    pdfDocument.setFontSize(11);
    pdfDocument.text(
      "MEDICINE DETAILS\n\n\n" +
      "Medicine code: " + medicine.medicine.medicineCode + "\n\n" +
      "Name: " + medicine.medicine.name + "\n\n" + 
      "Type: " + medicine.medicine.medicineType + "\n\n" +
      "Form: " + medicine.medicine.form + "\n\n" + 
      "Composition: " + medicine.medicine.composition + "\n\n" + 
      "Manufacturer: " + medicine.medicine.manufacturer + "\n\n" +
      "Average grade: " + medicine.medicine.averageRating + "\n\n" +
      "Additional comments: " + medicine.medicine.additionalComments, 0, 10
    );
    pdfDocument.save(medicine.medicine.name + " details");
  }

  back() {
    if (this.authService.getTokenData()?.role === 'ROLE_PATIENT') { 
      this.router.navigate(['/UserHomePage']);
    } else if (this.authService.getTokenData()?.role === 'ROLE_SYSTEM_ADMIN') {
      this.router.navigate(['/systemAdminProfilePage']);
    } else if (this.authService.getTokenData()?.role === 'ROLE_FARMACIST') {
      this.router.navigate(['/PharmacistHomePage']);
    } else if (this.authService.getTokenData()?.role === 'ROLE_DERMATLOGIST') {
      this.router.navigate(['/DermatologistHomePage']);
    } else if (this.authService.getTokenData()?.role === 'ROLE_PHARMACY_ADMIN') {
      this.router.navigate(['/pharmacyAdmin']);
    }
  }
}