import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular_material.module';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { PharmacistHomePageComponent } from './pharmacist-home-page/pharmacist-home-page.component';
import { DermatologistHomePageComponent } from './dermatologist-home-page/dermatologist-home-page.component';
import { PharmacistPatientComponent } from './pharmacist-patients/pharmacist-patient.component';
import { DermatologistPatientComponent } from './dermatologist-patients/dermatologist-patient.component';
import { PharmacistCalendarComponent } from './pharmacist-calendar/pharmacist-calendar.component';
import { DermatologistCalendarComponent } from './dermatologist-calendar/dermatologist-calendar.component';
import { AddMedicineComponent } from './add-medicine/add-medicine.component';
import { SystemAdminProfilePageComponent } from './system-admin-profile-page/system-admin-profile-page.component';
import { SupplierProfilePageComponent } from './supplier-profile-page/supplier-profile-page.component';

import { PharmacyAdminComponent } from './components/pharmacy-admin/pharmacy-admin.component';
import { ListOfPharmacistsComponent } from './components/list-of-pharmacists/list-of-pharmacists.component';
import { AddNewPharmacistComponent } from './components/add-new-pharmacist/add-new-pharmacist.component';
import { AddNewMedicineComponent } from './components/add-new-medicine/add-new-medicine.component';
import { ListOfMedicineComponent } from './components/list-of-medicine/list-of-medicine.component';
import { AddNewDermatologistComponent } from './components/add-new-dermatologist/add-new-dermatologist.component';
import { ListOfDermatologistsComponent } from './components/list-of-dermatologists/list-of-dermatologists.component';

FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin,
  timeGridPlugin,
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
    PharmacistCalendarComponent,
    DermatologistCalendarComponent,
    AddMedicineComponent,
    SystemAdminProfilePageComponent,
    SupplierProfilePageComponent,
    PharmacyAdminComponent,
    ListOfPharmacistsComponent,
    AddNewPharmacistComponent,
    AddNewMedicineComponent,
    ListOfMedicineComponent,
    AddNewDermatologistComponent,
    ListOfDermatologistsComponent,
  ],
  imports: [
    BrowserModule,
    FullCalendarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'register', component: RegistrationComponent },
      { path: 'login', component: LoginComponent },
      { path: 'UserHomePage', component: UserHomePageComponent },
      { path: 'PharmacistHomePage', component: PharmacistHomePageComponent },
      {
        path: 'DermatologistHomePage',
        component: DermatologistHomePageComponent,
      },
      {
        path: 'PharmacistPatientComponent',
        component: PharmacistPatientComponent,
      },
      {
        path: 'DermatologistPatientComponent',
        component: DermatologistPatientComponent,
      },
      {
        path: 'PharmacistCalendarComponent',
        component: PharmacistCalendarComponent,
      },
      {
        path: 'DermatologistCalendarComponent',
        component: DermatologistCalendarComponent,
      },
      { path: 'addMedicine', component: AddMedicineComponent },
      {
        path: 'systemAdminProfilePage',
        component: SystemAdminProfilePageComponent,
      },
      { path: 'supplierProfilePage', component: SupplierProfilePageComponent },

      {
        path: 'DermatologistHomePage',
        component: DermatologistHomePageComponent,
      },
      {
        path: 'PharmacistPatientComponent',
        component: PharmacistPatientComponent,
      },
      {
        path: 'DermatologistPatientComponent',
        component: DermatologistPatientComponent,
      },
      { path: 'pharmacyAdmin', component: PharmacyAdminComponent },
      { path: 'listOfPharmacists', component: ListOfPharmacistsComponent },
      { path: 'addNewPharmacist', component: AddNewPharmacistComponent },
      { path: 'listOfMedicine', component: ListOfMedicineComponent },
      { path: 'addNewMedicine', component: AddNewMedicineComponent },
      {
        path: 'listOfDermatologists',
        component: ListOfDermatologistsComponent,
      },
      { path: 'addNewDermatologist', component: AddNewDermatologistComponent },
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
