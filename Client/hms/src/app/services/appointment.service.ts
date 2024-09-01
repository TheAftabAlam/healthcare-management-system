import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/ApiResponse';
import { Appointment } from '../models/Appointment';
import { FilterObject } from '../models/FilterObject';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private httpClient :HttpClient) { }

  private apiUrl = 'http://localhost:8888'

  getAppointments(filterObject:FilterObject){
    return this.httpClient.post<ApiResponse<Appointment>>(this.apiUrl+'/appointment/search',filterObject);
  }

  getAppointment(id:number){
    return this.httpClient.get<ApiResponse<Appointment>>(this.apiUrl+'/appointment/byId/'+id);
  }

  addOrUpdateAppointment(appointment : Appointment){
    return this.httpClient.post<ApiResponse<Appointment>>(this.apiUrl+'/appointment/',appointment);
  }

  updateStatus(filterObject:FilterObject ){
    return this.httpClient.post<ApiResponse<String>>(this.apiUrl+"/appointment/update-fields",filterObject);
  }
}
