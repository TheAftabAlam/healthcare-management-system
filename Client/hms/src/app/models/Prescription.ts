import { Address } from "cluster";
import { Inventory } from "./Inventory";
import { User } from "./user.model";

export class Prescription{
    id:number | undefined;
    patientId: number |undefined;
    doctorId:number |undefined;
    doctorName:string = '';
    patientName:string ='';
    note:string='';
    status:string='';
    bloodPressure:string='';
    bloodSugar:string='';
    medicines: Medicine[] = [];
    prescriptionDate:number | undefined;
    doctor:User = new User();
    patient:User = new User() ;
}

export class Medicine{
    id: number | undefined;
    inventoryId: number | undefined;
    inventory : Inventory | undefined;
    dosage:string = '';
    frequency:string ='';
    duration:string ='';
    note:string='';
    
}