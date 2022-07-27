import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs/internal/Observable';
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RefereeService {

  private refereeUrl = `${environment.apiBaseUrl}/referee`;

  constructor(private httpClient: HttpClient) { }

  // Get All Referees
  public getReferees(): Observable<any> {
    return this.httpClient.get<any[]>(`${this.refereeUrl}`);
  }

  // Get Referee
  public getRefereeById(refereeId: any): Observable<any> {
    return this.httpClient.get<any>(`${this.refereeUrl}/${refereeId}`);
  }

  // Add New Referee
  public addReferee(refereeForm: any): Observable<any> {
    return this.httpClient.post<any>(`${this.refereeUrl}`, refereeForm);
  }

  // Update Referee
  public updateReferee(refereeForm: any, refereeId: any): Observable<any>{
    return this.httpClient.put<any>(`${this.refereeUrl}/${refereeId}`, refereeForm);
  }

  // Delete Referee
  public deleteReferee(refereeId: any): Observable<any>{
    return this.httpClient.delete<any>(`${this.refereeUrl}/${refereeId}`);
  }
}
