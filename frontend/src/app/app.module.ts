import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule} from '@angular/forms';

import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular_material.module';
import { FullCalendarModule } from '@fullcalendar/angular';
import { LayoutModule } from '@angular/cdk/layout';

import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';

import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { PharmacistHomePageComponent } from './pharmacist-home-page/pharmacist-home-page.component';
import { DermatologistHomePageComponent } from './dermatologist-home-page/dermatologist-home-page.component';
import { PharmacistPatientComponent } from './pharmacist-patients/pharmacist-patient.component';
import { DermatologistPatientComponent } from './dermatologist-patients/dermatologist-patient.component';
import { PatientProfileNavbarComponent } from './patient-profile-navbar/patient-profile-navbar.component';
import { ChangePatientDataComponent } from './change-patient-data/change-patient-data.component';
import { PharmacistCalendarComponent } from './pharmacist-calendar/pharmacist-calendar.component';
import { DermatologistCalendarComponent } from './dermatologist-calendar/dermatologist-calendar.component';
import { AddMedicineComponent } from './add-medicine/add-medicine.component';
import { SystemAdminProfilePageComponent } from './system-admin-profile-page/system-admin-profile-page.component';
import { SupplierProfilePageComponent } from './supplier-profile-page/supplier-profile-page.component';

import { SearchPharmacyComponent } from './search-pharmacy/search-pharmacy.component';

import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';

import { PharmacistAppointmentCreationComponent } from './pharmacist-appointment-creation/pharmacist-appointment-creation.component';
import { DermatologistChoosePredefinedComponent } from './dermatologist-choose-predefined/dermatologist-choose-predefined.component';
import { DermatologistAppointmentCreationComponent } from './dermatologist-appointment-creation/dermatologist-appointment-creation.component';




FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin,
  timeGridPlugin
]);

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    UserHomePageComponent,
    PharmacistHomePageComponent,
    DermatologistHomePageComponent,
    PharmacistPatientComponent,
    DermatologistPatientComponent,
    PatientProfileNavbarComponent,
    ChangePatientDataComponent,
    PharmacistCalendarComponent,
    DermatologistCalendarComponent,
    AddMedicineComponent,
    SystemAdminProfilePageComponent,
    SupplierProfilePageComponent,
    SearchPharmacyComponent,

    PharmacistAppointmentCreationComponent,
    DermatologistAppointmentCreationComponent,
    DermatologistChoosePredefinedComponent,
    DermatologistChoosePredefinedComponent

  ],
  imports: [
    BrowserModule,
    MatTableModule,
    MatPaginatorModule,
    FullCalendarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    RouterModule.forRoot([
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'register', component: RegistrationComponent },
      { path: 'login', component: LoginComponent },
      { path: 'UserHomePage', component: UserHomePageComponent },
      { path: 'PharmacistHomePage', component: PharmacistHomePageComponent },
      { path: 'DermatologistHomePage', component: DermatologistHomePageComponent },
      { path: 'PharmacistPatientComponent', component: PharmacistPatientComponent},
      { path: 'DermatologistPatientComponent', component: DermatologistPatientComponent},
      { path: 'PatientProfile', component:PatientProfileNavbarComponent},
      { path: 'PatientChangeData', component:ChangePatientDataComponent},
      { path: 'PharmacistCalendarComponent', component: PharmacistCalendarComponent},
      { path: 'DermatologistCalendarComponent', component: DermatologistCalendarComponent},
      { path: 'addMedicine', component: AddMedicineComponent },
      { path: 'systemAdminProfilePage', component: SystemAdminProfilePageComponent},
      { path: 'supplierProfilePage', component: SupplierProfilePageComponent},
      { path: 'searchPharmacy', component:SearchPharmacyComponent},
      { path: 'PharmacistAppointmentCreationComponent', component: PharmacistAppointmentCreationComponent},
      { path: 'DermatologistAppointmentCreationComponent', component: DermatologistAppointmentCreationComponent},
      { path: 'DermatologistChoosePredefinedComponent', component:DermatologistChoosePredefinedComponent}
    ]),
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule

  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }