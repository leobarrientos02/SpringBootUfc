import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefereesComponent} from "./components/referee/referee.component";
import { FightsComponent } from "./components/fight/fight.component";
import { FightersComponent } from "./components/fighter/fighter.component";
import { HomeComponent } from "./components/home/home.component";

const routes: Routes = [
  {path: "referee", component: RefereesComponent},
  {path: "fight", component: FightsComponent},
  {path: "fighter", component: FightersComponent},
  {path: "home", component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
