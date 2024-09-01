import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from 'src/app/models/Appointment';
import { FilterObject } from 'src/app/models/FilterObject';
import { User } from 'src/app/models/user.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-edit-appointment',
  templateUrl: './add-edit-appointment.component.html',
  styleUrls: ['./add-edit-appointment.component.css']
})
export class AddEditAppointmentComponent implements OnInit {
  appointment: Appointment = new Appointment();
  appointmentTypes: string[] = ['SCHEDULED', 'OVERDUE', 'COMPLETED', 'CANCELLED'];

  patients: User[] = [];
  doctors: User[] = [];
  filterObject: FilterObject = new FilterObject();

  constructor(
    private appointmentService: AppointmentService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam !== null) {
        const id = +idParam;
        if (!isNaN(id)) {
          this.loadAppointment(id);
        }
      }
      this.loadPatients();
      this.loadDoctors();
    });
    
  }

  loadAppointment(id: number): void {
    this.appointmentService.getAppointment(id).subscribe({
      next: (response) => {
        console.log(response.data['appointment'])
        if (response.status === 200 && response.data) {
          this.appointment = response.data['appointment'];
        } else {
          console.error('Error fetching appointment:', response);
        }
      },
      error: (error) => console.error('Error:', error)
    });
  }

  loadPatients(): void {
    this.filterObject.patientFlag = true;
    this.userService.getUsers(this.filterObject).subscribe(response => {
      if (response.data && response.data['user']) {
        this.patients = response.data['user'].dataList;
      }
    });
  }

  loadDoctors(): void {
    this.filterObject.doctorFlag = true;
    this.filterObject.patientFlag = false;
    this.userService.getUsers(this.filterObject).subscribe(response => {
      if (response.data && response.data['user']) {
        this.doctors = response.data['user'].dataList;
      }
    });
  }

  saveAppointment(): void {
    this.appointmentService.addOrUpdateAppointment(this.appointment).subscribe(response => {
      if (response.status === 200) {
        this.router.navigate(['/appointment']);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/appointment']);
  }

  onAppointmentTypeChange(data: any): void {
    console.log('Selected appointment type:', data.appointmentType);
    // Handle the change, e.g., update the backend or local state
  }
}
