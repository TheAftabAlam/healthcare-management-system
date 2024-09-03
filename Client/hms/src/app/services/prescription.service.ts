import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FilterObject } from '../models/FilterObject';
import { ApiResponse } from '../models/ApiResponse';
import { Prescription } from '../models/Prescription';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  private apiBaseUrl:string = "http://localhost:8888"

  constructor(
    private httpClient :HttpClient

  ) { }

  getPrescriptions(filterObject :FilterObject){
    return this.httpClient.post<ApiResponse<Prescription>>(this.apiBaseUrl+"/prescription/search",filterObject);
  }
  getById(id :number){
    return this.httpClient.post<ApiResponse<Prescription>>(this.apiBaseUrl+"/prescription/byId/",id);
  }
  saveOrUpdate(prescription :Prescription){
    return this.httpClient.post<ApiResponse<Prescription>>(this.apiBaseUrl+"/prescription/",prescription);
  }
}
