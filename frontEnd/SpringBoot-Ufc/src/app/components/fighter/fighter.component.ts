import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FighterService } from "../../services/Fighter/fighter.service";

@Component({
  selector: 'app-fighter',
  templateUrl: './fighter.component.html',
  styleUrls: ['./fighter.component.scss']
})
export class FighterComponent implements OnInit {

  public fighters: any = [];
  constructor(private fighterService: FighterService) { }

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
}
