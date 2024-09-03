import { Component, OnInit } from '@angular/core';
import { FilterObject } from '../models/FilterObject';
import { AppointmentService } from '../services/appointment.service';
import { Appointment } from '../models/Appointment';

import {ChangeDetectionStrategy} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import { MatDialog } from '@angular/material/dialog';
import { MyDialogComponentComponent } from '../my-dialog-component/my-dialog-component.component';

@Component({
  selector: 'app-daily-patient-list',
  templateUrl: './daily-patient-list.component.html',
  styleUrls: ['./daily-patient-list.component.css']
})
export class DailyPatientListComponent implements OnInit {
  appointments: Appointment[] = [];
  filterObject: FilterObject = new FilterObject();

  constructor(
    private appointmentService: AppointmentService,
    public dialog: MatDialog 
  ) { }

  ngOnInit(): void {
    this.fetchTodaysAppointments();
  }

  fetchTodaysAppointments() {
    this.filterObject.appointmentType = 'SCHEDULED'
    this.filterObject.appointmentDate = new Date().getTime(); // Assuming you're passing an epoch time
    this.appointmentService.getAppointments(this.filterObject).subscribe({
      next: (response) => {
        this.appointments = response.data['appointments'].dataList;
        if(this.appointments && this.appointments.length==0){
          this.openDialog()
        }
      },
      error: (err) => {
        console.error('Error fetching appointments:', err);
      }
    });
  }

  formatDate(epochTime: number): string {
    const date = new Date(epochTime);
    return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
  }

  changeType(id:number,type:string){
    this.filterObject.id=id;
    this.filterObject.appointmentType=type;
    this.appointmentService.updateStatus(this.filterObject).subscribe({
      next:(response)=>{
        this.fetchTodaysAppointments();
      }
    })
  }

  openDialog() {
    console.log(this.appointments.length===0);
      const dialogRef = this.dialog.open(MyDialogComponentComponent, {
        width: '250px',
        data: { message: 'No Appointment Scheduled Today!',title: "Appointments" }
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
  }

}
