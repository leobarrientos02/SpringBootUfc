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
  public fights: any[] = [];
  public arraySize: any;

  ngOnInit(): void {
    this.getAllFights();
  }

  public getAllFights(){
    this.fightService.getAllFights().subscribe(
      (response: any) =>{
        this.fights = response;
        this.getFight(Math.floor(Math.random() * response.length) + 1);
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  public getFight(fightId: any){
    this.fightService.getFightById(fightId).subscribe(
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

  public goToRefereePage(refereeId: any){
    this.router.navigate([`referee/${refereeId}`]);
  }

  public goToFightPage(id: any){
    this.router.navigate([`fight/${id}`]);
  }
}
