import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-supplier-write-offers',
  templateUrl: './supplier-write-offers.component.html',
  styleUrls: ['./supplier-write-offers.component.css']
})
export class SupplierWriteOffersComponent implements OnInit, AfterViewInit  {

  constructor(private fb: FormBuilder) { }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  displayedColumns: string[] = ['number', 'medicineCode', 'name', 'amount'];
  data = 
  [
      {number: 1, medicineCode: 'S123', name: 'medicine1', amount: 20},
      {number: 2, medicineCode: 'S124', name: 'medicine2', amount: 5},
      {number: 3, medicineCode: 'S125', name: 'medicine3', amount: 10},
      {number: 4, medicineCode: 'S126', name: 'medicine4', amount: 7},
      {number: 5, medicineCode: 'S127', name: 'medicine5', amount: 0},
      {number: 6, medicineCode: 'S128', name: 'medicine6', amount: 5},
      {number: 7, medicineCode: 'S129', name: 'medicine7', amount: 40},
      {number: 8, medicineCode: 'S130', name: 'medicine8', amount: 12},
      {number: 9, medicineCode: 'S131', name: 'medicine9', amount: 7},
      {number: 10, medicineCode: 'S132', name: 'medicine10', amount: 50},
      {number: 11, medicineCode: 'S133', name: 'medicine11', amount: 24},
      {number: 12, medicineCode: 'S134', name: 'medicine12', amount: 17},
      {number: 13, medicineCode: 'S135', name: 'medicine13', amount: 8},
      {number: 14, medicineCode: 'S136', name: 'medicine14', amount: 1},
  ];
  orders = ['order1', 'order2', 'order3'];
  dataSource = new MatTableDataSource<any>(this.data);
  offerForm!: FormGroup;
  purchaseOrdersForm!: FormGroup;
  selected = this.orders[0];

  ngOnInit(): void {
    this.offerForm = this.fb.group(
      {
        purchaseOrders: [this.selected],
        deliveryTime: ['', Validators.required],
        price: [0, Validators.required]
      }
    );
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.offerForm.controls[controlName].hasError(errorName);
  }

  sendOffer() {

  }

}
