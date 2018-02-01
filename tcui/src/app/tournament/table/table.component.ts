import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {getTeamsStats} from "../../reducers/reducers";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  stats: any;

  constructor(private store: Store<any>) {
  }

  ngOnInit() {
    this.stats = this.store.select(getTeamsStats);
  }

}
