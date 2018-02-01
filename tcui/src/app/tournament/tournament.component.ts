import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {AddPlayerAction, getTournament, ToggleTournamentAction} from "../reducers/reducers";
import {Tournament} from "../model/tournament.model";
import {Store} from "@ngrx/store";
import {TournamentService} from "../services/tournament.service";
import {PlayerService} from "../services/player.service";
import {Player} from "../model/player.model";
import {Score} from "../model/score.model";
import {TeamStats} from "../model/team-stats.model";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent implements OnInit {

  @ViewChild("playerName")
  playerName: ElementRef;
  private selectedTournament: Tournament;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private store: Store<any>,
              private tournamentService: TournamentService,
              private playerService: PlayerService) {
  }

  ngOnInit() {
    this.route.paramMap.map((params: ParamMap) => params.get('id'))
      .subscribe(id => {
          this.tournamentService.getTournamentById(Number(id)).subscribe((tournament: Tournament) => {
            this.tournamentService.getTeamsStats(Number(id)).subscribe((teamsStats: Array<TeamStats>) => {
              this.store.dispatch(new ToggleTournamentAction(tournament, teamsStats));
            });
          });
        }
      );

    this.store.select(getTournament).subscribe(t => {
      this.selectedTournament = t;
    });
  }

  addPlayer() {
    let player: Player = {
      name: this.playerName.nativeElement.value,
      id: 0,
      tournament: {id: this.selectedTournament.id}
    };
    this.playerService.storePlayer(player).subscribe((p: Player) => this.store.dispatch(new AddPlayerAction(p)));
  }

  getScore(homeId: number, awayId: number): Score {
    if (!this.selectedTournament.scores) return null;
    let scores = this.selectedTournament.scores.filter(score => score.playerHome.id == homeId && score.playerAway.id == awayId);
    if (scores.length > 0) {
      return scores[0];
    }
    return null;
  }

}
