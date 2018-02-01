export class TeamStats {
  id: number;
  name?: string;
  played: number = 0;
  won: number = 0;
  drawn: number = 0;
  lost: number = 0;
  gf: number = 0;
  ga: number = 0;
  gd: number = 0;
  points: number = 0;


  public constructor(id: number) {
    this.id = id;
  }
}
