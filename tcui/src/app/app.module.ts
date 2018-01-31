import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {HomeComponent} from './home/home.component';
import {TournamentComponent} from './tournament/tournament.component';
import {TournamentService} from "./services/tournament.service";
import {StoreModule} from "@ngrx/store";
import {reducers} from "./reducers/reducers";
import {AppRoutingModule} from "./app-routing.module";
import {PlayerService} from "./services/player.service";
import { ScoreComponent } from './tournament/score/score/score.component';
import {ScoreService} from "./services/score.service";
import { TableComponent } from './tournament/table/table.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TournamentComponent,
    ScoreComponent,
    TableComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot(reducers),
    AppRoutingModule
  ],
  providers: [
    TournamentService,
    PlayerService,
    ScoreService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
