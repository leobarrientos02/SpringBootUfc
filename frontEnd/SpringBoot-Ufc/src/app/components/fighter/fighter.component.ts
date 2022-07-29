import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FightService } from 'src/app/services/Fight/fight.service';
import { FighterService } from 'src/app/services/Fighter/fighter.service';
import { FrontEndFunctionsService } from 'src/app/services/FrontendFunctions/front-end-functions.service';

@Component({
  selector: 'app-fighter',
  templateUrl: './fighter.component.html',
  styleUrls: ['./fighter.component.scss']
})
export class FighterComponent implements OnInit {

  constructor(
    private fighterService: FighterService,
    private fightService: FightService,
    private ARouter: ActivatedRoute,
    public fes: FrontEndFunctionsService,
    private router: Router) { }

  ngOnInit(): void {
    this.getFighter();
  }

  public fighter: any;
  public fights: any[] = [];
  public filteredFights: any[] = [];


  // Get Fighter data
  public getFighter(){
    let fighterId: any = this.ARouter.snapshot.paramMap.get('id');
    this.fighterService.getFighterById(fighterId).subscribe(
      (response: any) => {
        this.fighter = response;
        this.getFightersFights();
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

    // Get all fights
    public getFightersFights(){
      this.fightService.getAllFights().subscribe(
        (response: any) => {
          this.fights = response;
          this.filteredFights = this.fights.filter(f => {
            return f.fighter1.id == this.fighter.id || f.fighter2.id == this.fighter.id;
          });
          console.log(this.filteredFights);
        },
        (error: HttpErrorResponse) =>{
          console.log("Status Code: " + error.status + ", message: " + error.message);
        }
      );
    }

  // Update Fighter
  public onUpdateFighter(fighterForm: NgForm, fighterId: any){
    this.fighterService.updateFighter(fighterForm.value, fighterId).subscribe(
      (response: any) => {
        this.getFighter();
        this.fes.closeForm('update','Fighter');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Delete Fighter
  public onDeleteFighter(fighterId: any){
    this.fighterService.deleteFighter(fighterId).subscribe(
      (response: any) => {
        this.router.navigateByUrl('/fighter');
        this.fes.closeForm('delete','Fighter');
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }
}
