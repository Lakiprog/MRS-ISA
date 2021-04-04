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
import { UserHomePageComponent } from './user-home-page/user-home-page.component'
import { PharmacistHomePageComponent } from './pharmacist-home-page/pharmacist-home-page.component';
import { DermatologistHomePageComponent } from './dermatologist-home-page/dermatologist-home-page.component';
import { PharmacistPatientComponent } from './pharmacist-patients/pharmacist-patient.component';
import { DermatologistPatientComponent } from './dermatologist-patients/dermatologist-patient.component';
import { PharmacistCalendarComponent } from './pharmacist-calendar/pharmacist-calendar.component';
import { DermatologistCalendarComponent } from './dermatologist-calendar/dermatologist-calendar.component';
import { AddMedicineComponent } from './add-medicine/add-medicine.component';
import { SystemAdminProfilePageComponent } from './system-admin-profile-page/system-admin-profile-page.component';


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
    PharmacistCalendarComponent,
    DermatologistCalendarComponent,
    AddMedicineComponent,
    SystemAdminProfilePageComponent
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
      { path: 'DermatologistHomePage', component: DermatologistHomePageComponent },
      { path: 'PharmacistPatientComponent', component: PharmacistPatientComponent},
      { path: 'DermatologistPatientComponent', component: DermatologistPatientComponent},
      { path: 'PharmacistCalendarComponent', component: PharmacistCalendarComponent},
      { path: 'DermatologistCalendarComponent', component: DermatologistCalendarComponent},
      { path: 'addMedicine', component: AddMedicineComponent },
      { path: 'systemAdminProfilePage', component: SystemAdminProfilePageComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }