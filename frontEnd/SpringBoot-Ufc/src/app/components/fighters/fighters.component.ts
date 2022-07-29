import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FighterService } from "../../services/Fighter/fighter.service";
import { Router } from '@angular/router';
import { FrontEndFunctionsService } from 'src/app/services/FrontendFunctions/front-end-functions.service';

@Component({
  selector: 'app-fighter',
  templateUrl: './fighters.component.html',
  styleUrls: ['./fighters.component.scss']
})
export class FightersComponent implements OnInit {

  public fighters: any = [];
  public fighter: any;
  constructor(
    private fighterService: FighterService,
    public fes: FrontEndFunctionsService,
    public router: Router) { }

  ngOnInit(): void {
    this.getAllFighters();
  }

  // Get all fighters
  public getAllFighters(){
    this.fighterService.getAllFighters().subscribe(
      (response: any) => {
        this.fighters = response;
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Get Fighter by id
  public getFighterById(fighterId: any){
    this.fighterService.getFighterById(fighterId).subscribe(
      (response: any) => {
        this.fighter = response;
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Add fighter
  public onAddFighter(fighterForm: NgForm){
    this.fighterService.addFighter(fighterForm.value).subscribe(
      (response: any) => {
        this.fes.closeForm('add','Fighter');
        this.getAllFighters();
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message)
      }
    )
  }
}
