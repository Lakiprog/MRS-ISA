import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { DateValidator } from './DateValidator';
import { SupplierWriteOffersService } from './supplier-write-offers.service';

@Component({
  selector: 'app-supplier-write-offers',
  templateUrl: './supplier-write-offers.component.html',
  styleUrls: ['./supplier-write-offers.component.css']
})
export class SupplierWriteOffersComponent implements OnInit, AfterViewInit  {

  constructor
  (
    private fb: FormBuilder, 
    private supplierWriteOffersService: SupplierWriteOffersService,
    private _snackBar: MatSnackBar
  ) { }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumns: string[] = ['medicineCode', 'name', 'quantity'];
  data = [];
  orders = [];
  dataSource = new MatTableDataSource<any>(this.data);
  offerForm!: FormGroup;
  purchaseOrdersForm!: FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  ngOnInit(): void {
    this.offerForm = this.fb.group(
      {
        orderName: ['', Validators.required],
        deliveryTime: ['', Validators.required],
        price: [0, Validators.required]
      }, {validator: DateValidator}
    );
    this.supplierWriteOffersService.getOrders().subscribe(
      data => {
        this.orders = data
      }
    )
    this.getMedicineSupply();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.offerForm.controls[controlName].hasError(errorName);
  }

  get deliveryTime() {
    return this.offerForm.get('deliveryTime');
  }

  checkDate() {
    if (this.offerForm.hasError('dateInvalid')) {
      this.offerForm.get('deliveryTime')?.setErrors([{'dateInvalid': true}]);
    }
  }

  getMedicineSupply() {
    this.supplierWriteOffersService.getMedicineSupply().subscribe(
      response => {
        this.data = response;
        this.dataSource = new MatTableDataSource<any>(this.data);
      }
    )
  }

  writeOffer() {
    this.supplierWriteOffersService.writeOffer(this.offerForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
      }, 
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
