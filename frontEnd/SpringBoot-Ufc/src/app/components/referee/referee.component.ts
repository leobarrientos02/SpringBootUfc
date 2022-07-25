import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RefereeService } from "../../services/Referee/referee.service";

@Component({
  selector: 'app-referee',
  templateUrl: './referee.component.html',
  styleUrls: ['./referee.component.scss']
})
export class RefereeComponent implements OnInit {

  constructor(public refereeService: RefereeService) { }

  ngOnInit(): void {
    this.getAllReferees();
  }
  public referee: any;
  public referees: any[] = [];

  public getAllReferees(){
    this.refereeService.getReferees().subscribe(
      (response: any) => {
        console.log(response);
        this.referees = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public getRefereeById(refereeId: any){
    this.refereeService.getReferee(refereeId).subscribe(
      (response: any) => {
        console.log(response);
        this.referee = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  // DOM Functions
  public openForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.add('showShadow');
    const form = document.getElementById(`${formType}Referee`);
    form?.classList.add('showForm');
  }

  public closeForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}Referee`);
    form?.classList.remove('showForm');
  }

}
