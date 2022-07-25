import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RefereeComponent } from './components/referee/referee.component';
import { RefereeService } from './services/Referee/referee.service';

@NgModule({
  declarations: [
    AppComponent,
    RefereeComponent
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
