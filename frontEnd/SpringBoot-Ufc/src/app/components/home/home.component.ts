import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FightService } from "../../services/Fight/fight.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private fightService: FightService) { }

  public fightData: any = {};

  ngOnInit(): void {
    this.getAllFights();
  }

  public getAllFights(){
    this.fightService.getAllFights().subscribe(
      (response: any) =>{
        console.log(response);
        this.fightData = response[0];
      },
      (error: HttpErrorResponse) => {
        console.log("Status Code: " + error.status + ", message: " + error.message);
      }
    )
  }

}
