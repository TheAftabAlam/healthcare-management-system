import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, map, catchError, of } from 'rxjs';
import { ApiResponse } from '../models/ApiResponse';
import { CommonListTO } from '../models/CommonListTO';
import { User } from '../models/user.model';
import { FilterObject } from '../models/FilterObject';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiBaseUrl = 'http://localhost:8888/user'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getUsers(filter:FilterObject): Observable<ApiResponse<User>> {
    return this.http.post<ApiResponse<User>>(this.apiBaseUrl+"/search",filter);
  }

  addOrUpdateUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiBaseUrl+"/", user);
  }
  getUser(id: number): Observable<ApiResponse<any>> {
    return this.http.get<ApiResponse<any>>(this.apiBaseUrl + "/byId/" + id);
  }
  
}
