import { Component, OnInit } from '@angular/core';
import { FilterObject } from '../models/FilterObject';
import { PrescriptionService } from '../services/prescription.service';
import { Prescription } from '../models/Prescription';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prescription',
  templateUrl: './prescription.component.html',
  styleUrls: ['./prescription.component.css']
})
export class PrescriptionComponent implements OnInit {

  prescriptions:Prescription[]=[];
  displayedColumns:string[] = ['doctorName','patientName','bloodPressure','prescriptionDate','note','status','actions'];
  filterObject: FilterObject = new FilterObject();
  constructor(
    private prescriptionService:PrescriptionService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.fetchPrescriptions(this.filterObject);
  }


  fetchPrescriptions(filterObject: FilterObject){
    this.prescriptionService.getPrescriptions(this.filterObject).subscribe({
      next:(resposne)=>{
        if(resposne && resposne.data){
          this.prescriptions=resposne.data['prescription'].dataList;
        }
      },error(error){
        console.log("error occur: "+error);
      }
    })
  }

  addEditPrescription(prescriptionId:any){
    const url = prescriptionId ? '/prescription/edit/'+prescriptionId : '/prescription/edit/new';
    this.router.navigate([url]);
  }

}
