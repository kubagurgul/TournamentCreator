import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TournamentService} from "../services/tournament.service";
import {Tournament} from "../model/tournament.model";
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/fromEvent";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import {ToggleTournamentAction} from "../reducers/reducers";
import {Store} from "@ngrx/store";
import "rxjs/add/operator/do";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('tournamentId')
  tournamentId: ElementRef;

  @ViewChild('tournamentName')
  tournamentName: ElementRef;

  isIdValid = false;


  constructor(private router: Router, private service: TournamentService, private store: Store<any>) {
  }

  ngOnInit() {
    Observable.fromEvent(this.tournamentId.nativeElement, 'keyup')
      .do(() => this.isIdValid = false)
      .map((event: any) => Number(event.target.value))
      .debounceTime(500)
      .distinctUntilChanged()
      .subscribe(
        (tournamentId: number) => this.service.getTournamentExistsById(tournamentId)
          .subscribe(
            exists => {
              this.isIdValid = exists;
              console.log(exists);
            }
          ));
  }

  createTournament() {
    console.log(this.tournamentName.nativeElement.value);
    let tournament: Tournament = {
      id: -1,
      name: this.tournamentName.nativeElement.value,
      url: "",
      players: [],
      scores: []
    };
    this.service.storeTournament(tournament).toPromise().then(t => this.router.navigate(['/tournament/' + t.id]));

  }

  goToTournament() {
    this.router.navigate(['/tournament/' + this.tournamentId.nativeElement.value]);
  }

}
