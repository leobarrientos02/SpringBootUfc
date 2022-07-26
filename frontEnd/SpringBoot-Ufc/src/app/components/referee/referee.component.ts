import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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
}
