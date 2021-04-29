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
  paginatorMedicineSupply!: MatPaginator;

  @ViewChild(MatPaginator)
  paginatorOrders!: MatPaginator;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['medicineCode', 'name', 'quantity'];
  displayedColumnsOrders: string[] = ['orderName', 'medicineCode', 'name', 'quantity'];
  medicineSupplyData = [];
  orderData = [];
  orders = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  orderDataSource = new MatTableDataSource<any>(this.orderData);
  dataSource1 = new MatTableDataSource<any>()
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
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
    this.orderDataSource.paginator = this.paginatorOrders
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
        this.medicineSupplyData = response;
        this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
      }
    )
  }

  getPurchaseOrders() {
    this.supplierWriteOffersService.getOrderByName(this.offerForm.get('orderName')?.value).
      subscribe(
        data => {
          this.orderData = data;
          this.orderDataSource = new MatTableDataSource<any>(this.orderData);
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
