import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ToggleTournamentAction} from "../reducers/reducers";
import {Tournament} from "../model/tournament.model";
import {Store} from "@ngrx/store";
import {TournamentService} from "../services/tournament.service";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent implements OnInit {

  private title$ = "";

  constructor(private route: ActivatedRoute, private router: Router, private store: Store<any>, private service: TournamentService) {
  }

  ngOnInit() {
    this.route.paramMap.map((params: ParamMap) => params.get('id'))
      .subscribe(id => this.service.getTournamentById(Number(id)).subscribe((tournament: Tournament) => {
        this.store.dispatch(new ToggleTournamentAction(tournament))
      }));
  }

}
