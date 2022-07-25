import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RefereeComponent } from './components/referee/referee.component';
import { RefereeService } from './services/Referee/referee.service';
import { NavComponent } from './components/nav/nav.component';
import { FightComponent } from './components/fight/fight.component';
import { FighterComponent } from './components/fighter/fighter.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    RefereeComponent,
    NavComponent,
    FightComponent,
    FighterComponent,
    HomeComponent
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
