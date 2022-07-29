import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FrontEndFunctionsService } from 'src/app/services/FrontendFunctions/front-end-functions.service';
import { RefereeService } from "../../services/Referee/referee.service";

@Component({
  selector: 'app-referee',
  templateUrl: './referees.component.html',
  styleUrls: ['./referees.component.scss']
})
export class RefereesComponent implements OnInit {

  constructor(
    public refereeService: RefereeService,
    public fes: FrontEndFunctionsService,
    public router: Router) { }

  ngOnInit(): void {
    this.getAllReferees();
  }
  public referees: any[] = [];
  public referee: any;

  public getAllReferees(){
    this.refereeService.getReferees().subscribe(
      (response: any) => {
        this.referees = response;
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    );
  }

  public onAddReferee(refereeForm: NgForm): void{
    this.refereeService.addReferee(refereeForm.value).subscribe(
      (response: any) => {
        this.getAllReferees();
        this.fes.closeForm('add','Referee');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  public onUpdateReferee(refereeForm: NgForm, refereeId: any): void{
    this.refereeService.updateReferee(refereeForm, refereeId).subscribe(
      (response: any) => {
        this.getAllReferees();
        this.fes.closeForm('update','Referee');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }
}
