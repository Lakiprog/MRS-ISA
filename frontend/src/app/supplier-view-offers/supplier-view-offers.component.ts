import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { SupplierViewOffersService } from './supplier-view-offers.service';

@Component({
  selector: 'app-supplier-view-offers',
  templateUrl: './supplier-view-offers.component.html',
  styleUrls: ['./supplier-view-offers.component.css']
})
export class SupplierViewOffersComponent implements OnInit, AfterViewInit {
  displayedColumnsOffers: string[] = ['orderName', 'deliveryDate', 'price', 'offerStatus'];
  filterOptions = ['ACCEPTED', 'REJECTED', 'PENDING'];
  offersData = [];
  offersDataSource = new MatTableDataSource<any>(this.offersData);

  constructor(private supplierViewOffersService: SupplierViewOffersService) { }

  @ViewChild(MatPaginator)
  paginatorOffers!: MatPaginator;

  ngOnInit(): void {
    this.supplierViewOffersService.getOffers().subscribe(
      data => {
        this.offersData = data;
        this.offersDataSource = new MatTableDataSource<any>(this.offersData);
      }
    )
    this.offersDataSource.filterPredicate = (data, filter) =>
      (data.offerStatus.indexOf(filter) !== -1);
  }

  ngAfterViewInit(): void {
    this.offersDataSource.paginator = this.paginatorOffers;
  }

  applyFilter(option: string) {
    const filterValue = option;
    this.offersDataSource.filter = filterValue;
  }
}

