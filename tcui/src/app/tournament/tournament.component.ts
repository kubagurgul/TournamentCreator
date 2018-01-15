import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent implements OnInit {

  private title$ = "";

  constructor(private route: ActivatedRoute,  private router: Router) { }

  ngOnInit() {
    this.route.paramMap.map((params: ParamMap) => params.get('id')).subscribe(id => this.title$ = "Tournament : " + id);
  }

}
