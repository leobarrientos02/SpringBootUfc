import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RefereesComponent } from './components/referees/referees.component';
import { RefereeService } from './services/Referee/referee.service';
import { NavComponent } from './components/nav/nav.component';
import { FightsComponent } from './components/fights/fights.component';
import { FightersComponent } from './components/fighters/fighters.component';
import { HomeComponent } from './components/home/home.component';
import { FighterComponent } from './components/fighter/fighter.component';
import { RefereeComponent } from './components/referee/referee.component';
import { FightComponent } from './components/fight/fight.component';

@NgModule({
  declarations: [
    AppComponent,
    RefereesComponent,
    NavComponent,
    FightsComponent,
    FightersComponent,
    HomeComponent,
    FighterComponent,
    RefereeComponent,
    FightComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [RefereeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
