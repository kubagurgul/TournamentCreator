import {Tournament} from "./tournament.model";

export interface Player {
  id: number;
  name?: string;
  tournament?: Tournament;
}
