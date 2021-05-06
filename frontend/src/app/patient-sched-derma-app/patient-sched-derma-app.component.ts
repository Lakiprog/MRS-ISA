import { Component, OnInit } from '@angular/core';
import { PatientSchedDermaAppService } from './patient-sched-derma-app.service';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-patient-sched-derma-app',
  templateUrl: './patient-sched-derma-app.component.html',
  styleUrls: ['./patient-sched-derma-app.component.css']
})
export class PatientSchedDermaAppComponent implements OnInit {

  displayedColumns: string[] = ["DATE", "TIME", "DERMATOLOG","DERMATOLOGIST PHONE", "DERMATOLOGIST E-MAIL", "RATING", "PRICE"];
  dataSource:any;
  elems:any;

  
  freeIsEmpty:boolean = false;
  notEmpty:boolean = false;
 

  constructor(public service:PatientSchedDermaAppService) { 
    this.service.getAllDerApp().subscribe((data2:any)=>{
      this.elems = data2;
      this.dataSource = new MatTableDataSource(this.elems);
      if(this.dataSource == []){
        this.freeIsEmpty = true;
      }else{
        this.notEmpty = true;
      }
      }) 
  }

  ngOnInit(): void {
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  

}
