import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefereesComponent} from "./components/referees/referees.component";
import { FightsComponent } from "./components/fights/fight.component";
import { FightersComponent } from "./components/fighters/fighters.component";
import { HomeComponent } from "./components/home/home.component";
import { FighterComponent } from './components/fighter/fighter.component';
import { RefereeComponent } from './components/referee/referee.component';

const routes: Routes = [
  {path: "referee", component: RefereesComponent},
  {path: "referee/:id", component: RefereeComponent},
  {path: "fight", component: FightsComponent},
  {path: "fighter", component: FightersComponent},
  {path: "fighter/:id", component: FighterComponent},
  {path: "home", component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
