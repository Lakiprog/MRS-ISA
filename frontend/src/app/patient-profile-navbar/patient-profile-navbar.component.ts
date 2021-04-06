import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { PatientProfileNavbarService } from './patient-profile-navbar.service';

@Component({
  selector: 'app-patient-profile-navbar',
  templateUrl: './patient-profile-navbar.component.html',
  styleUrls: ['./patient-profile-navbar.component.css']
})
export class PatientProfileNavbarComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  patient:any;

  constructor(private breakpointObserver: BreakpointObserver, private service:PatientProfileNavbarService) {
    this.service.getPatient().subscribe((data:any) => {this.patient = data; console.log(this.patient)})
  }

}
