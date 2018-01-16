import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Tournament} from "../model/tournament.model";
import "rxjs/add/operator/map";
import {Store} from "@ngrx/store";
import {ToggleTournamentAction} from "../reducers/reducers";
import {Observable} from "rxjs/Observable";

@Injectable()
export class TournamentService {
  constructor(private http: HttpClient, private store: Store<any>) {

  }

  public getTournamentById(id: number): Observable<any> {
    return this.http.get("http://localhost:4200/api/tournaments/" + id);
  }

  public getTournamentExistsById(id: number): Observable<any> {
    return this.http.get("http://localhost:4200/api/tournaments/exists/" + id);
  }

  public storeTournament(tournament: Tournament): any {
    return this.http.post("http://localhost:4200/api//tournaments/store", tournament).subscribe(x=> console.log(x));
  }

}
