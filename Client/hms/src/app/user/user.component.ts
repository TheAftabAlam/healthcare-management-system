import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../services/user.service';
import { ApiResponse } from '../models/ApiResponse';
import { MatDialog } from '@angular/material/dialog';
import { AddOrEditComponent } from './add-or-edit/add-or-edit.component';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FilterObject } from '../models/FilterObject';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  isLoading = false; 
  users: User[] = [];
  displayedColumns: string[] = ['name','type', 'phoneNumber', 'dateOfBirth', 'email', 'emergencyContact','status', 'actions'];
  dataSource = new MatTableDataSource<User>(); // Initialize MatTableDataSource
  filterObject : FilterObject = new FilterObject();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private userService: UserService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private router :Router
  ) {}

  ngOnInit() {
    this.fetchPatients();
  }

  fetchPatients() {
    this.userService.getUsers(this.filterObject).subscribe({
      next: (response: ApiResponse<User>) => {
        if (response && response.data) {
          if (response.status === 200) {
            const userKey = 'user';
            const userData = response.data[userKey];
    
            if (userData && userData.dataList) {
              const patients = userData.dataList; // Access the dataList
    
              // Set the data for the table
              this.dataSource.data = patients;
              this.dataSource.paginator = this.paginator; // Set the paginator
              this.dataSource.sort = this.sort; // Set the sort
            } else {
              console.error('Invalid data structure for the key:', userKey, userData);
            }
          }
        } else {
          console.error('Invalid response structure', response);
        }
      },
      error: (error: any) => {
        console.error('Error fetching patients', error);
      }
    });
  }
  
  
  
  
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = filterValue;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  applyFilterFromButton(){
    
  }


  deletePatient(user: User) {
    user.deletedFlag = true;  // Set the deleted flag to true for the user
  
    this.userService.addOrUpdateUser(user).subscribe(() => {
      const message = 'User deleted successfully!';
  
      // Show a snackbar notification
      this.snackBar.open(message, 'Close', {
        duration: 3000,            // Duration the snackbar is displayed (in milliseconds)
        verticalPosition: 'top',   // Positioning of the snackbar ('top' or 'bottom')
        horizontalPosition: 'center' // Horizontal alignment ('start', 'center', 'end', 'left', 'right')
      });

      this.fetchPatients();

    });
  }
  

  toggleActiveStatus(user: User) {
    this.isLoading = true;
    if(user.status == 'ACTIVE')
      user.status = 'DEACTIVE';
    else{
      user.status = 'ACTIVE';
    }
    this.userService.addOrUpdateUser(user).subscribe({
      next: (response) => {
        this.fetchPatients(); 
      },
      error: (error) => {
          console.error('Error updating user status', error);
          user.status = user.status === 'ACTIVE' ? 'DEACTIVE' : 'ACTIVE';
      },
      complete: () => {
          this.isLoading = false;
      }
    })
    this.fetchPatients();
  }


  openAddOrEditDialog(user?: User): void {
    const dialogRef = this.dialog.open(AddOrEditComponent, {
      data: { user }, // Pass both user and type
      width: '50%',
      maxHeight: '90vh', // Set max height for the dialog
      autoFocus: false, 
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.fetchPatients();
      }
    });
  }

  viewUser(user: any): void {
    this.router.navigate(['/user', user.id]);
  }

  // stopPropagation(event: Event): void {
  //   event.stopPropagation();
  // }
}
