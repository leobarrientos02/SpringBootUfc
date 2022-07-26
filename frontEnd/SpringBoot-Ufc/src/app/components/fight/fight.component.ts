import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FightService } from "../../services/Fight/fight.service";

@Component({
  selector: 'app-fight',
  templateUrl: './fight.component.html',
  styleUrls: ['./fight.component.scss']
})
export class FightComponent implements OnInit {

  constructor(private fightService: FightService) { }

  public fights: any[] = [];

  ngOnInit(): void {
    this.getAllFights();
  }

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

}
