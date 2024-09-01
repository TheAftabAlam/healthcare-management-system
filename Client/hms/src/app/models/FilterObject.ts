import { User } from './user.model';

export class FilterObject {
  limit: number | undefined;
  page: number = 1; 
  firstName: string = ''; 
  lastName: string = ''; 
  specialization: string = ''; 
  email: string = '';
  patientId: number | null = null; 
  doctorId: number | null = null; 
  status: string = ''; 
  doctorFlag: boolean = false; 
  patientFlag: boolean = false; 
  appointmentDate: number | null = null;
  appointmentType:string ='';
  id:number| undefined 
}