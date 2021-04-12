import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { PatientProfileNavbarComponent } from './patient-profile-navbar/patient-profile-navbar.component';

const routes: Routes = [
  { path: 'UserHomePage', component: UserHomePageComponent },
  { path: 'PatientProfile', component: PatientProfileNavbarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [UserHomePageComponent, PatientProfileNavbarComponent]
