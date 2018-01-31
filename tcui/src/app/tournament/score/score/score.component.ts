import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Score} from "../../../model/score.model";
import {Store} from "@ngrx/store";
import {AddScoreAction, getTournament} from "../../../reducers/reducers";
import {ScoreService} from "../../../services/score.service";

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.scss']
})
export class ScoreComponent implements OnInit {
  @Input()
  private score?: Score;
  @Input()
  private idHome: number;
  @Input()
  private idAway: number;
  @ViewChild("homeInput")
  homeInput: ElementRef;
  @ViewChild("awayInput")
  awayInput: ElementRef;

  private selectedTournament;

  constructor(private store: Store<any>, private service: ScoreService) {
  }

  ngOnInit() {
    this.store.select(getTournament).subscribe(t => this.selectedTournament = t);
  }

  private findScore(): Score {
    return this.selectedTournament.scores.find((score: Score) => score.playerHome.id == this.idHome && score.playerAway.id == this.idAway);
  }

  private storeScore() {
    let score: Score = {
      id: 0,
      playerHome: { id: this.idHome },
      playerAway: { id: this.idAway },
      homeResult: Number(this.homeInput.nativeElement.value),
      awayResult: Number(this.awayInput.nativeElement.value),
      tournament: { id: this.selectedTournament.id },
      result: null
    };
    console.log("save score {}", score);
    this.service.storeScore(score).subscribe((s: Score) => {
      this.store.dispatch(new AddScoreAction(s))
    });
  }

}
