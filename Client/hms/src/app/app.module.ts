import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSidenavModule } from '@angular/material/sidenav'; // Import MatSidenavModule
import { MatListModule } from '@angular/material/list'; // Import MatListModule
import { MatIconModule } from '@angular/material/icon';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppointmentComponent } from './appointment/appointment.component'; // Import MatIconModule
import { FormsModule } from '@angular/forms';
import { UserComponent } from './user/user.component'; // Import FormsModule
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HighchartsChartModule } from 'highcharts-angular';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { AddOrEditComponent } from './user/add-or-edit/add-or-edit.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AddEditAppointmentComponent } from './appointment/add-edit-appointment/add-edit-appointment.component';
import { ViewComponent } from './user/view/view.component';  // Import MatProgressSpinnerModule
import { RouterModule } from '@angular/router';
import { MatChipsModule } from '@angular/material/chips';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatOptionModule } from '@angular/material/core';

import { MatSelectModule } from '@angular/material/select';
import { InventoryComponent } from './inventory/inventory.component';
import { AddEditInventoryComponent } from './inventory/add-edit-inventory/add-edit-inventory.component';
import { ViewInventoryComponent } from './inventory/view-inventory/view-inventory.component';
import { DailyPatientListComponent } from './daily-patient-list/daily-patient-list.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MyDialogComponentComponent } from './my-dialog-component/my-dialog-component.component';
import { PrescriptionComponent } from './prescription/prescription.component';
import { AddEditPrescriptionComponent } from './prescription/add-edit-prescription/add-edit-prescription.component'; // Import MatProgressBarModule



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AppointmentComponent,
    UserComponent,
    AddOrEditComponent,
    AddEditAppointmentComponent,
    ViewComponent,
    InventoryComponent,
    AddEditInventoryComponent,
    ViewInventoryComponent,
    DailyPatientListComponent,
    MyDialogComponentComponent,
    PrescriptionComponent,
    AddEditPrescriptionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatGridListModule ,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatFormFieldModule,
    MatMenuModule,
    HighchartsChartModule,
    HttpClientModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    RouterModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatOptionModule,
    MatSelectModule,
    MatChipsModule,
    MatProgressBarModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
