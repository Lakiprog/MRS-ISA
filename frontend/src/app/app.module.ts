import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { PharmacistAppointmentCreationComponent } from './pharmacist-appointment-creation/pharmacist-appointment-creation.component';
import { DermatologistChoosePredefinedComponent } from './dermatologist-choose-predefined/dermatologist-choose-predefined.component';
import { DermatologistAppointmentCreationComponent } from './dermatologist-appointment-creation/dermatologist-appointment-creation.component';
import { SearchPharmacyComponent } from './search-pharmacy/search-pharmacy.component';
import { RegisterPharmaciesComponent } from './register-pharmacies/register-pharmacies.component';

import { PharmacyAdminComponent } from './components/pharmacy-admin/pharmacy-admin.component';
import { ListOfPharmacistsComponent } from './components/list-of-pharmacists/list-of-pharmacists.component';
import { AddNewPharmacistComponent } from './components/add-new-pharmacist/add-new-pharmacist.component';
import { AddNewMedicineComponent } from './components/add-new-medicine/add-new-medicine.component';
import { ListOfMedicineComponent } from './components/list-of-medicine/list-of-medicine.component';
import { AddNewDermatologistComponent } from './components/add-new-dermatologist/add-new-dermatologist.component';
import { ListOfDermatologistsComponent } from './components/list-of-dermatologists/list-of-dermatologists.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import { SearchMedicineByIdComponent } from './components/search-medicine-by-id/search-medicine-by-id.component';

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
    PatientProfileNavbarComponent,
    ChangePatientDataComponent,
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
    PharmacistAppointmentCreationComponent,
    DermatologistAppointmentCreationComponent,
    DermatologistChoosePredefinedComponent,
    DermatologistChoosePredefinedComponent,
    SearchPharmacyComponent,
    RegisterPharmaciesComponent,
    UpdateMedicineComponent,
  ],
  imports: [
    BrowserModule,
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
      { path: 'PatientProfile', component: PatientProfileNavbarComponent },
      { path: 'PatientChangeData', component: ChangePatientDataComponent },
      {
        path: 'PharmacistAppointmentCreationComponent',
        component: PharmacistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistAppointmentCreationComponent',
        component: DermatologistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistChoosePredefinedComponent',
        component: DermatologistChoosePredefinedComponent,
      },
      { path: 'searchPharmacy', component: SearchPharmacyComponent },
      { path: 'registerPharmacies', component: RegisterPharmaciesComponent },
      { path: 'updateMedicine', component: UpdateMedicineComponent },
      { path: 'searchMedicineById', component: SearchMedicineByIdComponent },
    ]),
  ],

  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
