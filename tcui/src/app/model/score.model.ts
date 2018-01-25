import {Tournament} from "./tournament.model";
import {Player} from "./player.model";

export interface Score {
  id: number;
  playerHome: Player;
  playerAway: Player;
  homeResult: number,
  awayResult: number,
  result?: any;
  tournament: Tournament;
}
