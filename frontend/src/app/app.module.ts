import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { PharmacistProfilePageComponent } from './pharmacist-profile-page/pharmacist-profile-page.component';
import { DermatologistProfilePageComponent } from './dermatologist-profile-page/dermatologist-profile-page.component';
import { AuthService } from './login/auth.service';
import { TokenInterceptor } from './login/TokenInterceptor';
import { SystemAdminRoutes } from './user-routes/SystemAdminRoutes';
import { PharmacistRoutes } from './user-routes/PharmacistRoutes';
import { DermatologistRoutes } from './user-routes/DermatologistRoutes';
import { SupplierRoutes } from './user-routes/SupplierRoutes';
import { PharmacyAdminRoutes } from './user-routes/PharmacyAdminRoutes';
import { PatientRoutes } from './user-routes/PatientRoutes';
import { UserComplaintComponent } from './user-complaint/user-complaint.component';
import { PatientsComplaintsDataComponent } from './patients-complaints-data/patients-complaints-data.component';


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
    PharmacistProfilePageComponent,
    DermatologistProfilePageComponent,
    UserComplaintComponent,
    PatientsComplaintsDataComponent
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
      {
        path: 'UserHomePage',
        canActivate: [PatientRoutes],
        component: UserHomePageComponent,
      },
      {
        path: 'PharmacistHomePage',
        canActivate: [PharmacistRoutes],
        component: PharmacistHomePageComponent,
      },
      {
        path: 'DermatologistHomePage',
        canActivate: [DermatologistRoutes],
        component: DermatologistHomePageComponent,
      },
      {
        path: 'PharmacistPatientComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistPatientComponent,
      },
      {
        path: 'DermatologistPatientComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistPatientComponent,
      },
      {
        path: 'PharmacistCalendarComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistCalendarComponent,
      },
      {
        path: 'DermatologistCalendarComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistCalendarComponent,
      },
      {
        path: 'addMedicine',
        canActivate: [SystemAdminRoutes],
        component: AddMedicineComponent,
      },
      {
        path: 'systemAdminProfilePage',
        canActivate: [SystemAdminRoutes],
        component: SystemAdminProfilePageComponent,
      },
      {
        path: 'supplierProfilePage',
        canActivate: [SupplierRoutes],
        component: SupplierProfilePageComponent,
      },
      {
        path: 'pharmacyAdmin',
        canActivate: [PharmacyAdminRoutes],
        component: PharmacyAdminComponent,
      },
      {
        path: 'listOfPharmacists',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfPharmacistsComponent,
      },
      {
        path: 'addNewPharmacist',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewPharmacistComponent,
      },
      {
        path: 'listOfMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfMedicineComponent,
      },
      {
        path: 'addNewMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewMedicineComponent,
      },
      {
        path: 'listOfDermatologists',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfDermatologistsComponent,
      },
      {
        path: 'addNewDermatologist',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewDermatologistComponent,
      },
      {
        path: 'PatientProfile',
        canActivate: [PatientRoutes],
        component: PatientProfileNavbarComponent,
      },
      {
        path: 'PatientChangeData',
        canActivate: [PatientRoutes],
        component: ChangePatientDataComponent,
      },
      {
        path: 'PharmacistAppointmentCreationComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistAppointmentCreationComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistChoosePredefinedComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistChoosePredefinedComponent,
      },
      {
        path: 'searchPharmacy',
        component: SearchPharmacyComponent,
      },
      {
        path: 'registerPharmacies',
        canActivate: [SystemAdminRoutes],
        component: RegisterPharmaciesComponent,
      },
      {
        path: 'updateMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: UpdateMedicineComponent,
      },

      {
        path: 'PharmacistProfilePageComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistProfilePageComponent,
      },
      {
        path: 'DermatologistProfilePageComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistProfilePageComponent,
      },

      { path: 'userComplaint', component: UserComplaintComponent},
      { path: 'patientsComplaintsData', component: PatientsComplaintsDataComponent}
    ]),
  ],

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    AuthService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
