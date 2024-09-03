// src/app/models/user.model.ts

export class User {
    id!: number ;
    firstName: string = '';
    lastName: string ='';
    email!: string;
    phoneNumber!: string;
    dateOfBirth!: string;
    doctorFlag!: boolean;
    patientFlag!: boolean;
    deletedFlag!:boolean;
    staffFlag!: boolean;
    specialization!: string;
    staffRole!: string;
    status!:string;
    emergencyContact!: string;
    dateJoined!: string;
    gender!: 'MALE' | 'FEMALE' | 'OTHER';
    addresses!: Address[];

  }
  export interface Address {
    id: number;
    streetAddress: string;
    city: string;
    state: string;
    postalCode: string;
    country: string;
}

  

  