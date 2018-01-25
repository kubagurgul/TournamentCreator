import { Injectable } from '@angular/core';
import {Player} from "../model/player.model";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PlayerService {

  constructor(private http: HttpClient) { }

  public storePlayer(p: Player) : Observable<any> {
    console.log("saving {}", p);
    return this.http.post("http://localhost:4200/api/players/create", p);
  }

}
