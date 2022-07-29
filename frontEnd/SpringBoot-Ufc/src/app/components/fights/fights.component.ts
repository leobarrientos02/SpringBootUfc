import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FighterService } from 'src/app/services/Fighter/fighter.service';
import { RefereeService } from 'src/app/services/Referee/referee.service';
import { FightService } from "../../services/Fight/fight.service";

@Component({
  selector: 'app-fight',
  templateUrl: './fights.component.html',
  styleUrls: ['./fights.component.scss']
})
export class FightsComponent implements OnInit {

  constructor(
    private fightService: FightService,
    private router: Router,
    private fighterService: FighterService,
    private refereeService: RefereeService
    ) { }

  public fights: any[] = [];
  public filtered: any[] = [];
  public weightClasses: string[] = [
    "Heavyweight",
    "Light Heavyweight",
    "Middleweight",
    "Welterweight",
    "Lightweight",
    "Featherweight",
    "Bantamweight",
    "Flyweight",
  ];

  public fightTypes: string[] = [
    "Three Rounds",
    "Five Rounds",
    "Championship"
  ];

  public result: String = "tba";

  public locations: string[] = [
    "T-Mobile Arena in Las Vegas, Nevada",
    "MGM Grand Arena in Lav Vegas, Nevada",
    "Honda Center in Anaheim, California",
    "Prudential Center in Newark, New Jersey",
    "Toyota Center in Houston, Texas",
    "Madison Square Garden Arena in New York, New York",
    "Scotiabank Arena in Toronto, Ontario, Canada",
    "CentreBell Arena in Montreal, Quebec, Canada",
    "Jeunesse Arena in Rio de Janeiro, Brazil",
    "United Center in Chicago, Illinois",
    "The O2 Arena in London, United Kingdom",
    "UFC Apex in Las Vegas, Nevada"
  ];

  public referees: any[] = [];
  public fighters: any[] = [];

  ngOnInit(): void {
    this.getAllFights();
    this.getAllReferees();
    this.getAllFighters();
  }
  // Get all fights
  public getAllFights(){
    this.fightService.getAllFights().subscribe(
      (response: any) => {
        this.fights = response;
      },
      (error: HttpErrorResponse) =>{
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Add fight
  public onAddFight(fightForm: NgForm){
    this.fightService.addFight(fightForm.value).subscribe(
      (response: any) => {
        this.closeForm('add');
        this.getAllFights();
      },
      (error: HttpErrorResponse) =>{
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Get all fighters
  public getAllFighters(){
    this.fighterService.getAllFighters().subscribe(
      (response: any) => {
        this.fighters = response;
      },
      (error: HttpErrorResponse) =>{
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Get all referees
  public getAllReferees(){
    this.refereeService.getReferees().subscribe(
      (response: any) => {
        this.referees = response;
      },
      (error: HttpErrorResponse) =>{
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // DOM Functions
  public openForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.add('showShadow');
    const form = document.getElementById(`${formType}Fight`);
    form?.classList.add('showForm');
  }

  public closeForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}Fight`);
    form?.classList.remove('showForm');
  }

  public goToFightPage(id: any){
    this.router.navigate([`fight/${id}`]);
  }

  public goToFighterPage(id: any){
    this.router.navigate([`fighter/${id}`]);
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

  public selectFighterOne(fighter: any){
      this.filtered = this.fighters.filter(f => {
        return f.name !== fighter.name;
      });
  }
}
