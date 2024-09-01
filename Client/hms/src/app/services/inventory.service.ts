import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FilterObject } from '../models/FilterObject';
import { ApiResponse } from '../models/ApiResponse';
import { Inventory } from '../models/Inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  constructor(private httpClient:HttpClient) { }

  private apiBaseUrl : string = "http://localhost:8888";


  addOrUpdate(inventory:Inventory){
    return this.httpClient.post<ApiResponse<Inventory>>(this.apiBaseUrl+"/inventory/",inventory);
  }
  getInventory(id:number){
    return this.httpClient.get<ApiResponse<Inventory>>(this.apiBaseUrl+"/inventory/byId/"+id);
  }
  searchInventory(filterObject:FilterObject){
    return this.httpClient.post<ApiResponse<Inventory>>(this.apiBaseUrl+"/inventory/search",filterObject);
  }
 
}
