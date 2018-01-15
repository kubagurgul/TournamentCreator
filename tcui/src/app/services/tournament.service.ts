import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Tournament} from "../model/tournament.model";
import "rxjs/add/operator/map";
import {Store} from "@ngrx/store";
import {ToggleTournamentAction} from "../reducers/reducers";

@Injectable()
export class TournamentService {
  constructor(private http: HttpClient, private store: Store<any>) {

  }

  public getTournamentById(id: number): any {
    return this.http.get("http://localhost:4200/api/tournaments/" + id).subscribe((x: Tournament) => this.store.dispatch(new ToggleTournamentAction(x)));
  }

  public getTournamentExistsById(id: number): any {
    return this.http.get("http://localhost:4200/api/tournaments/exists/" + id).subscribe(x => console.log(x));
  }

  public storeTournament(tournament: Tournament): any {
    return this.http.post("http://localhost:4200/api//tournaments/store", tournament).subscribe(x=> console.log(x));
  }

}
