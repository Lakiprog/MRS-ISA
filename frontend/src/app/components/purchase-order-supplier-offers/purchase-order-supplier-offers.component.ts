import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { DummyModel } from 'src/app/models/dummy-model';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { DisplayActivePurchaseOrdersComponent } from '../display-active-purchase-orders/display-active-purchase-orders.component';
import { Location } from '@angular/common';
import { PurchaseOrderSupplier } from 'src/app/models/purchase-order-supplier';

@Component({
  selector: 'app-purchase-order-supplier-offers',
  templateUrl: './purchase-order-supplier-offers.component.html',
  styleUrls: ['./purchase-order-supplier-offers.component.css'],
})
export class PurchaseOrderSupplierOffersComponent implements OnInit {
  displayedColumnsPurchaseOrderOffersList: string[] = [
    'deliveryDate',
    'price',
    'purchaseOrderName',
    'accept',
    'reject',
  ];
  purchaseOrderSupplierList!: PurchaseOrderSupplier[];
  pharmacyAdmin!: PharmacyAdmin;
  today = new Date();

  purchaseOrderSupplierListSource = new MatTableDataSource<any>(
    this.purchaseOrderSupplierList
  );

  purchaseOrderId!: DummyModel;
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

  accept(purachseOrderSupplier: PurchaseOrderSupplier) {
    this.pharmacyAdminService
      .acceptPurchaseOrderSupplier(purachseOrderSupplier, this.pharmacyAdmin)
      .subscribe((res) => this.updateLists());
  }

  reject(purachseOrderSupplier: PurchaseOrderSupplier) {
    this.pharmacyAdminService
      .rejectPurchaseOrderSupplier(purachseOrderSupplier, this.pharmacyAdmin)
      .subscribe((res) => this.updateLists());
  }

  updateLists() {
    this.pharmacyAdminService
      .getPendingPurchaseOrderSupplier(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.purchaseOrderSupplierList = res;
        for (let pos of this.purchaseOrderSupplierList) {
          let date = new Date(pos.purchaseOrder.dueDateOffer);

          if (this.today.getTime() < date.getTime()) {
            pos.isAfterToday = true;
          } else {
            pos.isAfterToday = false;
          }
        }

        this.purchaseOrderSupplierListSource = new MatTableDataSource<any>(
          this.purchaseOrderSupplierList
        );
        this.purchaseOrderSupplierListSource.paginator =
          this.paginatorPurchaseOrder;
      });
  }
  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
