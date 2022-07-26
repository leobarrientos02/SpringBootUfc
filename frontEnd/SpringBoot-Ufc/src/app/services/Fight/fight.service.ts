import { Injectable } from '@angular/core';
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FightService {

  private fightUrl = `${environment.apiBaseUrl}/fight`;
  constructor(private http: HttpClient) { }

  // Get all fights
  public getAllFights(): Observable<any[]> {
    return this.http.get<any[]>(`${this.fightUrl}`);
  }

  // Get A Fight
  public getFightById(fightId: any): Observable<any> {
    return this.http.get<any>(`${this.fightUrl}/${fightId}`, fightId);
  }

  // Add a Fight
  public addFight(fightForm: any): Observable<any> {
    return this.http.post<any>(`${this.fightUrl}`, fightForm);
  }

  // Update a Fight

  // Delete a Fight
}
