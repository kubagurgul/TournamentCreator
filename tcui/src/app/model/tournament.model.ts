import {Score} from "./score.model";
import {Player} from "./player.model";

export interface Tournament {
  id: number;
  name: string;
  url: string;
  scores: Array<Score>;
  players: Array<Player>;
}
