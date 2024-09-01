import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserComponent } from './user/user.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { ViewComponent } from './user/view/view.component';
import { AddEditAppointmentComponent } from './appointment/add-edit-appointment/add-edit-appointment.component';
import { InventoryComponent } from './inventory/inventory.component';
import { AddEditInventoryComponent } from './inventory/add-edit-inventory/add-edit-inventory.component';
import { ViewInventoryComponent } from './inventory/view-inventory/view-inventory.component';
import { DailyPatientListComponent } from './daily-patient-list/daily-patient-list.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user', component: UserComponent },
  { path: 'user/:id', component: ViewComponent },
  { path: 'appointment', component: AppointmentComponent },
  { path: 'appointment/:id', component: AddEditAppointmentComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: 'inventory/edit/:id', component: AddEditInventoryComponent },
  { path: 'inventory/view/:id', component: ViewInventoryComponent },
  { path: 'daily-patient-list', component: DailyPatientListComponent },

  { path: '**', redirectTo: '' } // Wildcard route for a 404 page or redirect to home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
