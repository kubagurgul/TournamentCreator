import {Tournament} from "../model/tournament.model";
import {Action, ActionReducerMap, createFeatureSelector, createSelector} from "@ngrx/store";
import {Player} from "../model/player.model";
import {Score} from "../model/score.model";
import {TeamStats} from "../model/team-stats.model";

export const TOGGLE_TOURNAMENT = 'TOGGLE_TOURNAMENT';
export const ADD_PLAYER = 'ADD_PLAYER';
export const ADD_SCORE = 'ADD_SCORE';

export interface AppState {
  tournamentState: TournamentState
};

export interface TournamentState {
  tournament: Tournament;
  teamsStats: Array<TeamStats>;
};

export const initialState: TournamentState = {
  tournament: null,
  teamsStats: null
};

export class ToggleTournamentAction implements Action {
  type = TOGGLE_TOURNAMENT;

  constructor(public tournament: Tournament, public teamsStats: Array<TeamStats>) {
  };

}

export class AddPlayerAction implements Action {
  type = ADD_PLAYER;

  constructor(public payload: Player) {
  };
}

export class AddScoreAction implements Action {
  type = ADD_SCORE;

  constructor(public payload: Score) {
  }
}

function updateStats(state: TournamentState, score: Score) {
  let home: TeamStats = state.teamsStats.filter(s => s.id === score.playerHome.id)[0];
  let away: TeamStats = state.teamsStats.filter(s => s.id === score.playerAway.id)[0];
  home.played = home.played++;
  away.played = away.played++;
  if ("DRAW" == score.result) {
    home.points = home.points++;
    away.points = away.points++;
    home.drawn = home.drawn++;
    away.drawn = away.drawn++;
  } else if ("HOME_WON" == score.result) {
    home.points = home.points + 3;
    home.won = home.won++;
    away.lost = away.lost++;
  } else {
    away.points = away.points + 3;
    home.lost = home.lost++;
    away.won = away.won++;
  }
  home.gf = home.gf + score.homeResult;
  home.ga = home.ga + score.awayResult;
  away.gf = away.gf + score.awayResult;
  away.ga = away.ga + score.homeResult;
}

export function tournamentReducer(state: TournamentState = initialState, action: any): TournamentState {
  switch (action.type) {
    case TOGGLE_TOURNAMENT:
      action.teamsStats.forEach((s: TeamStats) => s.name = action.tournament.players.filter(p => s.id === p.id)[0].name);
      return {tournament: action.tournament, teamsStats: action.teamsStats};
    case ADD_PLAYER: {
      let playerAdded: Player = action.payload;
      state.tournament.players.push(action.payload);
      state.teamsStats.push(new TeamStats(playerAdded.id));
      return state;
    }
    case ADD_SCORE: {
      let score = action.payload;
      state.tournament.scores.push(score);
      updateStats(state, score);
      state.teamsStats.sort((s1, s2) => s2.points - s1.points);
      return state;
    }
    default:
      return state;
  }
}

export const getTournamentState = createFeatureSelector<TournamentState>('tournamentState')

export const getTournament = createSelector(
  getTournamentState,
  (state: TournamentState) => state.tournament
);

export const getTeamsStats = createSelector(
  getTournamentState,
  (state: TournamentState) => state.teamsStats
);
export const reducers: ActionReducerMap<AppState> = {
  tournamentState: tournamentReducer
};
