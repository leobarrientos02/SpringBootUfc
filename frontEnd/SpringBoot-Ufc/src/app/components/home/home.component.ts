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

  public fightTypeFormatter(fightType: String){
    if(fightType == "CHAMPIONSHIP"){
      return "Championship";
    }else if(fightType == "THREE_ROUNDS"){
      return "3 Rounds";
    }else{
      return "5 Rounds";
    }
  }

  public resultFormatter(result: String){
    if(result == "DECISION"){
      return "Decision";
    }else if(result == "SPLIT_DECISION"){
      return "Split Decision";
    }else if(result == "KO"){
      return "Knockout";
    }else if(result == "UNANIMOUS_DECISION"){
      return "Unanimous Decision";
    }else if(result == "TKO"){
      return "Technical Knockout";
    }else if(result == "DRAW"){
      return "Draw";
    }else if(result == "Doctor Stoppage"){
      return "Doctor Stoppage";
    }else{
      return "TBA";
    }
  }
}
