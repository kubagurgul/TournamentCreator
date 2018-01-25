import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Score} from "../model/score.model";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ScoreService {

  constructor(private http: HttpClient) { }

  public storeScore(score: Score): Observable<any> {
    return this.http.post("http://localhost:4200/api/score/create", score);
  }

}
