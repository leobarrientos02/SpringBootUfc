import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RefereeService } from "../../services/Referee/referee.service";

@Component({
  selector: 'app-referee',
  templateUrl: './referee.component.html',
  styleUrls: ['./referee.component.scss']
})
export class RefereesComponent implements OnInit {

  constructor(public refereeService: RefereeService) { }

  ngOnInit(): void {
    this.getAllReferees();
  }
  public referees: any[] = [];
  public referee: any;

  public getAllReferees(){
    this.refereeService.getReferees().subscribe(
      (response: any) => {
        console.log(response);
        this.referees = response;
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    );
  }

  public getRefereeById(refereeId: any){
    this.refereeService.getReferee(refereeId).subscribe(
      (response: any) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  public onAddReferee(refereeForm: NgForm): void{
    this.refereeService.addReferee(refereeForm.value).subscribe(
      (response: any) => {
        console.log(response);
        this.getAllReferees();
        this.closeForm('add');
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
        this.closeForm('update');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  public editReferee: any;
  public deleteReferee: any;

  // DOM Functions
  public openForm(formType: any, referee: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.add('showShadow');
    const form = document.getElementById(`${formType}Referee`);
    form?.classList.add('showForm');

    if(formType == "update"){
      this.editReferee = referee;
    }else if(formType == "delete"){
      this.deleteReferee = referee;
    }

  }

  public closeForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}Referee`);
    form?.classList.remove('showForm');
  }
}
