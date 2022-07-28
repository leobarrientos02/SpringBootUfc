import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RefereeService } from 'src/app/services/Referee/referee.service';

@Component({
  selector: 'app-referee',
  templateUrl: './referee.component.html',
  styleUrls: ['./referee.component.scss']
})
export class RefereeComponent implements OnInit {

  constructor(
    private refereeService: RefereeService,
    private ARouter: ActivatedRoute,
    private router: Router,) { }
  public referee: any;

  ngOnInit(): void {
    this.getReferee();
  }

  // Get referee data
  public getReferee(){
    let refereeId: any = this.ARouter.snapshot.paramMap.get('id');
    this.refereeService.getRefereeById(refereeId).subscribe(
      (response: any) => {
        this.referee = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Update Referee
  public onUpdateReferee(updateReferee: NgForm, refereeId: any){
    this.refereeService.updateReferee(updateReferee.value, refereeId).subscribe(
      (response: any) => {
        this.closeForm('update');
        this.getReferee();
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    );
  }

  // Delete Referee
  public onDeleteReferee(refereeId: any){
    this.refereeService.deleteReferee(refereeId).subscribe(
      (response: any) => {
        this.router.navigateByUrl('/referee');
        this.closeForm('delete');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

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

  public closeAnyForm(){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById('updateReferee');
    form?.classList.remove('showForm');
    const form2 = document.getElementById('deleteReferee');
    form2?.classList.remove('showForm');
  }
}
