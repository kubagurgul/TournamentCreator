import {Tournament} from "../model/tournament.model";
import {Action, ActionReducerMap, createFeatureSelector, createSelector} from "@ngrx/store";
import {Player} from "../model/player.model";
import {Score} from "../model/score.model";

export const TOGGLE_TOURNAMENT = 'TOGGLE_TOURNAMENT';
export const ADD_PLAYER = 'ADD_PLAYER';
export const ADD_SCORE = 'ADD_SCORE';

export interface AppState {
  tournamentState: TournamentState
};

export interface TournamentState {
  tournament: Tournament
};

export const initialState: TournamentState = {
  tournament: null
};

export class ToggleTournamentAction implements Action {
  type = TOGGLE_TOURNAMENT;
  constructor(public payload: Tournament) {};

}

export class AddPlayerAction implements Action {
  type = ADD_PLAYER;
  constructor(public payload: Player) {};
}

export class AddScoreAction implements Action {
  type = ADD_SCORE;
  constructor(public payload: Score) {}
}

export function tournamentReducer(state: TournamentState = initialState, action: any): TournamentState {
  switch(action.type) {
    case TOGGLE_TOURNAMENT:
      return { tournament: action.payload };
    case ADD_PLAYER: {
      state.tournament.players.push(action.payload);
      return state;
    }
    case ADD_SCORE: {
      state.tournament.scores.push(action.payload);
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

export const reducers: ActionReducerMap<AppState> = {
  tournamentState: tournamentReducer
};
