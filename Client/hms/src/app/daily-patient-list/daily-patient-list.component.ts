import { Component, OnInit } from '@angular/core';
import { FilterObject } from '../models/FilterObject';
import { AppointmentService } from '../services/appointment.service';
import { Appointment } from '../models/Appointment';

import {ChangeDetectionStrategy} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';

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
        console.log(this.appointments);
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

}
