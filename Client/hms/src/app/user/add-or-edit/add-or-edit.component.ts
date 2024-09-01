import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-or-edit',
  templateUrl: './add-or-edit.component.html',
  styleUrls: ['./add-or-edit.component.css']
})
export class AddOrEditComponent implements OnInit {
  form: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private userService: UserService,
    private dialogRef: MatDialogRef<AddOrEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { user?: User, type?: 'PATIENT' | 'DOCTOR' }
  ) {
    this.form = this.fb.group({
      id:[''],
      firstName: ['',Validators.required],
      lastName: ['',Validators.required],
      email: [''],
      phoneNumber: ['',Validators.required],
      dateOfBirth: ['',Validators.required],
      emergencyContact: [''],
      addresses: this.fb.array(this.initAddresses(data?.user?.addresses || [])),
      patientFlag: [this.data?.user?.patientFlag || this.data?.type === 'PATIENT' || false],
      doctorFlag: [this.data?.user?.doctorFlag || this.data?.type === 'DOCTOR' || false],
      staffFlag: [this.data?.user?.staffFlag || false],
    });
  }
  get addressControls() {
    return (this.form.get('addresses') as FormArray).controls;
  }

  initAddresses(addresses: any[]): FormGroup[] {
    return addresses.map(address => this.fb.group({
      streetAddress: [address.streetAddress || ''],
      city: [address.city || ''],
      state: [address.state || '',Validators.required],
      postalCode: [address.postalCode || '',Validators.required],
      country: [address.country || '']
    }));
  }
  
  ngOnInit(): void {
    if (this.data && this.data.user) {
      this.isEditMode = true;
      this.form.patchValue(this.data.user);
    } else {
      this.isEditMode = false;
    }
  }

 

  addAddress() {
    const addresses = this.form.get('addresses') as FormArray;
    addresses.push(this.fb.group({
      streetAddress: [''],
      city: [''],
      state: [''],
      postalCode: [''],
      country: ['']
    }));
  }

  removeAddress(index: number) {
    const addresses = this.form.get('addresses') as FormArray;
    addresses.removeAt(index);
  }

  onSubmit(): void {
    if (this.form.valid) {
      const user: User = this.form.value;
      this.userService.addOrUpdateUser(user).subscribe(() => {
        this.dialogRef.close(true);
      });
      const message = this.isEditMode ? 'User updated successfully!' : 'User added successfully!';
      this.snackBar.open(message, 'Close', {
        duration: 3000, // Duration in milliseconds
        verticalPosition: 'top', // 'top' or 'bottom'
        horizontalPosition: 'center' // 'start', 'center', 'end', 'left', 'right'
      });
    }
  }
  

  onCancel(): void {
    this.dialogRef.close();
  }
}
