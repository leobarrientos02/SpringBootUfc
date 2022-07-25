import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefereeComponent} from "./components/referee/referee.component";
import { FightComponent } from "./components/fight/fight.component";
import { FighterComponent } from "./components/fighter/fighter.component";
import { HomeComponent } from "./components/home/home.component";

const routes: Routes = [
  {path: "referee", component: RefereeComponent},
  {path: "fight", component: FightComponent},
  {path: "fighter", component: FighterComponent},
  {path: "home", component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
