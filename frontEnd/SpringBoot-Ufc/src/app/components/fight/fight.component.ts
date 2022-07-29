import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FightService } from 'src/app/services/Fight/fight.service';
import { FighterService } from 'src/app/services/Fighter/fighter.service';
import { FrontEndFunctionsService } from 'src/app/services/FrontendFunctions/front-end-functions.service';
import { RefereeService } from 'src/app/services/Referee/referee.service';

@Component({
  selector: 'app-fight',
  templateUrl: './fight.component.html',
  styleUrls: ['./fight.component.scss']
})
export class FightComponent implements OnInit {
  constructor(
    private fightService: FightService,
    private router: Router,
    private ARouter: ActivatedRoute,
    private fighterService: FighterService,
    private refereeService: RefereeService,
    public fes: FrontEndFunctionsService
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
  public results: any[] = [
    "Decision",
    "Split Decision",
    "Unanimous Decision",
    "Knockout",
    "Technical Knockout",
    "Draw",
    "Doctor Stoppage",
  ];

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
  public fight: any;

  ngOnInit(): void {
    this.getFightById();
    this.getAllReferees();
    this.getAllFighters();
  }

  // Get Fight Id
  public getFightById(){
    let fightId: any = this.ARouter.snapshot.paramMap.get('id');
    this.fightService.getFightById(fightId).subscribe(
      (response: any) => {
        this.fight = response;
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

  // Delete Fight
  public onDeleteFight(fightId: any){
    this.fightService.deleteFight(fightId).subscribe(
      (response: any) => {
        this.router.navigateByUrl('/fight');
        this.fes.closeForm('delete','Fight');
      },
      (error: HttpErrorResponse) =>{
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Update Fight
  public onUpdateFight(fightForm: NgForm, fightId: any){
    this.fightService.updateFight(fightForm.value, fightId).subscribe(
      (response: any) => {
        this.getFightById();
        this.fes.closeForm('update','Fight');
        this.fes.closeForm('updateResult','Fight');
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

  public selectFighterOne(fighter: any){
    this.filtered = this.fighters.filter(f => {
      return f.name !== fighter.name;
    });
  }
}
