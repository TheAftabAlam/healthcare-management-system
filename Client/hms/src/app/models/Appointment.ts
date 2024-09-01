import { User } from "./user.model";

export class Appointment {
  public id: number = 0;
  public appointmentDate: Date = new Date();
  public status: string = '';
  public patientId: number = 0;
  public patient: User = {} as User;
  public doctorId: number = 0;
  public doctor: User = {} as User;
  public reasonForVisit: string = '';
  public appointmentType: string = '';
  public reminderSet: boolean = false;
  public reminderTime: Date = new Date();
  public cancellationReason: string = '';
  public tokenNumber:string = '';
}