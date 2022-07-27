import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FightService } from "../../services/Fight/fight.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private fightService: FightService, public router: Router) { }

  public fightData: any = {};

  ngOnInit(): void {
    this.getFight();
  }

  public getFight(){
    this.fightService.getFightById(1).subscribe(
      (response: any) =>{
        this.fightData = response;
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  public goToFighterPage(fighterId: any){
    this.router.navigate([`fighter/${fighterId}`]);
  }
}
