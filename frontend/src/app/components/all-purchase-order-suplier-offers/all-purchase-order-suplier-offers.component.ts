import { Component, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/login/auth.service';
import { DummyModel } from 'src/app/models/dummy-model';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrderSupplier } from 'src/app/models/purchase-order-supplier';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-all-purchase-order-suplier-offers',
  templateUrl: './all-purchase-order-suplier-offers.component.html',
  styleUrls: ['./all-purchase-order-suplier-offers.component.css'],
})
export class AllPurchaseOrderSuplierOffersComponent implements OnInit {
  displayedColumnsPurchaseOrderOffersList: string[] = [
    'deliveryDate',
    'price',
    'purchaseOrderName',
    'offerStatus',
  ];
  purchaseOrderSupplierList!: PurchaseOrderSupplier[];
  pharmacyAdmin!: PharmacyAdmin;
  purchaseOrderSupplierListSource = new MatTableDataSource<any>(
    this.purchaseOrderSupplierList
  );

  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private authService: AuthService,
    public dialog: MatDialog,
    private location: Location
  ) {}
  @ViewChild(MatPaginator)
  paginatorPurchaseOrder!: MatPaginator;

  ngOnInit(): void {
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
      this.updateLists();
    });
  }

  updateLists() {
    this.pharmacyAdminService
      .getAllPurchaseOrderSupplier(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.purchaseOrderSupplierList = res;
        this.purchaseOrderSupplierListSource = new MatTableDataSource<any>(
          this.purchaseOrderSupplierList
        );
        this.purchaseOrderSupplierListSource.paginator =
          this.paginatorPurchaseOrder;
      });
  }

  public doFilter = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.purchaseOrderSupplierListSource.filter = filterValue
      .trim()
      .toLowerCase();
    this.purchaseOrderSupplierListSource.paginator =
      this.paginatorPurchaseOrder;
  };
  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
