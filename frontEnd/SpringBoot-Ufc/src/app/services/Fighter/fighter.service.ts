import { Injectable } from '@angular/core';
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FighterService {

  constructor(private http: HttpClient) { }
  private fightersUrl = `${environment.apiBaseUrl}/fighters`;

  // Get all fighters
  public getAllFighters(): Observable<any[]>{
    return this.http.get<any[]>(`${this.fightersUrl}`);
  }


}
