import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FighterService } from 'src/app/services/Fighter/fighter.service';

@Component({
  selector: 'app-fighter',
  templateUrl: './fighter.component.html',
  styleUrls: ['./fighter.component.scss']
})
export class FighterComponent implements OnInit {

  constructor(
    private fighterService: FighterService,
    private ARouter: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.getFighter();
  }

  public fighter: any;

  // Get Fighter data
  public getFighter(){
    let fighterId: any = this.ARouter.snapshot.paramMap.get('id');
    this.fighterService.getFighterById(fighterId).subscribe(
      (response: any) => {
        this.fighter = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

  // Update Fighter
  public onUpdateFighter(fighterForm: NgForm, fighterId: any){
    console.log(fighterForm.value);
    this.fighterService.updateFighter(fighterForm.value, fighterId).subscribe(
      (response: any) => {
        this.getFighter();
        this.closeForm('update');
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
    const form = document.getElementById(`${formType}Fighter`);
    form?.classList.add('showForm');
  }

  public closeForm(formType: any){
    const shadow = document.getElementById('shadow');
    shadow?.classList.remove('showShadow');
    const form = document.getElementById(`${formType}Fighter`);
    form?.classList.remove('showForm');
  }
}
