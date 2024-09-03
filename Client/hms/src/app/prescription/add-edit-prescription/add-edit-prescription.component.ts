import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { FilterObject } from 'src/app/models/FilterObject';
import { Inventory } from 'src/app/models/Inventory';
import { Medicine, Prescription } from 'src/app/models/Prescription';
import { User } from 'src/app/models/user.model';
import { InventoryService } from 'src/app/services/inventory.service';
import { PrescriptionService } from 'src/app/services/prescription.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-edit-prescription',
  templateUrl: './add-edit-prescription.component.html',
  styleUrls: ['./add-edit-prescription.component.css']
})
export class AddEditPrescriptionComponent implements OnInit {
  prescription: Prescription = new Prescription();
  doctors: User[] = [];
  patients: User[] = [];
  medicineList: Medicine[] = [];
  inventories: Inventory[] = [];
  filterObject: FilterObject = new FilterObject();
  medicineDataSource = new MatTableDataSource<Medicine>(this.medicineList);
  displayedColumns: string[] = ['inventory', 'dose', 'frequency', 'duration'];

  constructor(
    private route: ActivatedRoute,
    private prescriptionService: PrescriptionService,
    private router: Router,
    private inventoryService: InventoryService,
    private userService: UserService,
    private cd: ChangeDetectorRef,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap) => {
      const idParam = paramMap.get('id');
      if (idParam) {
        const id = +idParam;
        if (!isNaN(id)) {
          this.prescriptionService.getById(id).subscribe({
            next: (response) => {
              if (response && response.data) {
                this.prescription = response.data;
                this.medicineList = this.prescription.medicines || [];
                this.medicineDataSource.data = [...this.medicineList];
              }
            },
            error: (error) => {
              console.warn(error);
            }
          });
        }
      }
    });

    this.fetchDoctors();
    this.fetchPatients();
    this.fetchInventories();
  }

  fetchDoctors() {
    this.filterObject.doctorFlag = true;
    this.userService.getUsers(this.filterObject).subscribe({
      next: (response) => {
        if (response && response.data) {
          this.doctors = response.data['user'].dataList;
        }
      },
      error: (error) => {
        console.warn(error);
      }
    });
  }

  fetchInventories() {
    this.filterObject.doctorFlag = false;
    this.inventoryService.searchInventory(this.filterObject).subscribe({
      next: (response) => {
        if (response && response.data) {
          this.inventories = response.data['inventory'].dataList;
        }
      },
      error: (error) => {
        console.warn(error);
      }
    });
  }

  fetchPatients() {
    this.filterObject.doctorFlag = false;
    this.filterObject.patientFlag = true;
    this.userService.getUsers(this.filterObject).subscribe({
      next: (response) => {
        if (response && response.data) {
          this.patients = response.data['user'].dataList;
        }
      },
      error: (error) => {
        console.warn(error);
      }
    });
  }

  addMedicine(): void {
    const newMedicine = new Medicine();
    this.medicineList = [...this.medicineList, newMedicine];
    this.medicineDataSource.data = [...this.medicineList];;
    this.cd.detectChanges();
  }
  

  removeMedicine(index: number): void {
    this.medicineList.splice(index, 1);
    this.medicineDataSource.data = [...this.medicineList]; // Update the dataSource
  }

  onSubmit(): void {
    this.prescription.medicines = this.medicineList;
    if (this.prescription.id) {
      this.prescriptionService.saveOrUpdate(this.prescription).subscribe(() => {
        this.router.navigate(['/prescriptions']);
      });
    } else {
      this.prescriptionService.saveOrUpdate(this.prescription).subscribe(() => {
        this.router.navigate(['/prescriptions']);
      });
    }
  }

  onCancel(): void {
    // Handle cancel action
  }
}
