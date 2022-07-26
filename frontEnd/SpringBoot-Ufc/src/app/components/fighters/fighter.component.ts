import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FighterService } from "../../services/Fighter/fighter.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-fighter',
  templateUrl: './fighter.component.html',
  styleUrls: ['./fighter.component.scss']
})
export class FightersComponent implements OnInit {

  public fighters: any = [];
  public fighter: any;
  constructor(private fighterService: FighterService, public router: Router) { }

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
        this.closeForm('add');
        this.getAllFighters();
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message)
      }
    )
  }

  // DOM Functions
  public openForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.add('showShadow');
    const form = document.getElementById(`${formType}Fighter`);
    form?.classList.add('showForm');
  }

  public closeForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}Fighter`);
    form?.classList.remove('showForm');
  }

  public goToFighterPage(fighterId: any){
    this.router.navigate([`fighter/${fighterId}`]);
  }
}
