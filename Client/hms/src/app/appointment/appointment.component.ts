import { Component, OnInit, ViewChild } from '@angular/core';
import { AppointmentService } from '../services/appointment.service';
import { ApiResponse } from '../models/ApiResponse';
import { Appointment } from '../models/Appointment';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { AddEditAppointmentComponent } from './add-edit-appointment/add-edit-appointment.component';
import { Router } from '@angular/router';
import { FilterObject } from '../models/FilterObject';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  displayedColumns: string[] = ['patientName','doctorName','tokenNumber','appointmentDate', 'appointmentType','status', 'actions'];
  dataSource = new MatTableDataSource<Appointment>(); // Initialize MatTableDataSource

  filterObject: FilterObject = new FilterObject();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private sAppointment: AppointmentService,
    private dialog: MatDialog,
    private router: Router

  ) {}

  ngOnInit(): void {
    this.fetchAppointments(this.filterObject);
  }

  fetchAppointments(filterObject:FilterObject): void {
    this.sAppointment.getAppointments(filterObject).subscribe({
      next: (response: ApiResponse<Appointment>) => {
        if (response && response.status === 200) {
          const appointments = response.data['appointments'].dataList || []; 
          this.dataSource.data = appointments;
          this.dataSource.paginator = this.paginator; // Set the paginator
          this.dataSource.sort = this.sort; // Set the sort
          // console.log(appointments)
        } else {
          console.error('Unexpected API response:', response);
        }
      },
      error: (error) => {
        console.error('Error fetching appointments:', error);
      }
    });
  }

  // openAddOrEditDialog(appointment?:Appointment){
  //   const dialogRef = this.dialog.open(AddEditAppointmentComponent,{
  //     data : {appointment},
  //     width: '50%',
  //     maxHeight : '90vh',
  //     autoFocus:false
  //   });
  //   dialogRef.afterClosed().subscribe(result =>{
  //     if(result){
  //       this.fetchAppointments();
  //     }
  //   })

  // }


  openAddOrEditDialog(appointmentId?: number): void {
    const url = appointmentId ? `/appointment/${appointmentId}` : '/appointment/new';
    this.router.navigate([url]);
  }
}
