import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RefereesComponent } from './components/referee/referee.component';
import { RefereeService } from './services/Referee/referee.service';
import { NavComponent } from './components/nav/nav.component';
import { FightsComponent } from './components/fight/fight.component';
import { FightersComponent } from './components/fighter/fighter.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    RefereesComponent,
    NavComponent,
    FightsComponent,
    FightersComponent,
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
