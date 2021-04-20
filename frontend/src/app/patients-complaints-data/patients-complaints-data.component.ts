import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { PatientsComplaintsDataService } from './patients-complaints-data.service';
import {MatTableDataSource} from '@angular/material/table';



@Component({
  selector: 'app-patients-complaints-data',
  templateUrl: './patients-complaints-data.component.html',
  styleUrls: ['./patients-complaints-data.component.css'],
  
})
export class PatientsComplaintsDataComponent implements OnInit {

  displayedColumns: string[] = ["No", "Text"];
  dataSource!: MatTableDataSource<any>;
  
 

  list : any = []
  
  
  expandedElement: any | null;

  constructor(private service:PatientsComplaintsDataService) { 

    this.dataSource = new MatTableDataSource( this.list );
    this.service.getCom().subscribe((data:any)=>{
      this.list = data;
      this.dataSource.data = this.list;
      console.log(this.dataSource.data)})
  }

  ngOnInit(): void {
  }

}
