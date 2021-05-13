import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { FileInput } from 'ngx-material-file-input';
import { MedicinePrescriptionService } from './medicine-prescription.service';

@Component({
  selector: 'app-medicine-prescription',
  templateUrl: './medicine-prescription.component.html',
  styleUrls: ['./medicine-prescription.component.css']
})
export class MedicinePrescriptionComponent implements OnInit, AfterViewInit {

  constructor
  (
    private fb: FormBuilder,
    private medicinePrescriptionService: MedicinePrescriptionService
  ) { }
  qrCodeForm!: FormGroup;
  displayedColumnsPharmacies: string[] = ['name', 'address', 'rating', 'totalPrice'];
  pharmacyData = [];
  pharmaciesDataSource = new MatTableDataSource<any>(this.pharmacyData);

  ngOnInit(): void {
    this.qrCodeForm = this.fb.group(
      {
        qrCode: ['']
      }
    );
  }

  @ViewChild(MatPaginator)
  paginatorPharmacies!: MatPaginator;

  ngAfterViewInit(): void {
    this.pharmaciesDataSource.paginator = this.paginatorPharmacies;
  }

  sortData(sort: Sort) {
    this.pharmaciesDataSource.data = this.pharmaciesDataSource.data.sort((a:any, b:any) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a['pharmacy']['name'], b['pharmacy']['name'], isAsc);
        case 'address': return this.compare(a['pharmacy']['address'], b['pharmacy']['address'], isAsc);
        case 'rating': return this.compare(a['pharmacy']['rating'], b['pharmacy']['rating'], isAsc);
        case 'totalPrice': return this.compare(a['total'], b['total'], isAsc);
        default: return 0;
      }
    });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  sendQrCode() {
      if (this.qrCodeForm.get('qrCode')?.value !== '') {
      const file_form: FileInput = this.qrCodeForm.get('qrCode')?.value;
      const file = file_form.files[0]; 
      const formData = new FormData();
      formData.append('qrCode', file);
      this.medicinePrescriptionService.sendQrCode(formData).subscribe(
        response => {
          this.pharmacyData = response;
          this.pharmaciesDataSource = new MatTableDataSource<any>(this.pharmacyData);
          this.pharmaciesDataSource.paginator = this.paginatorPharmacies;
        }
      )
    }
  }
}