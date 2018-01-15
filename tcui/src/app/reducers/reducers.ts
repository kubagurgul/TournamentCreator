import {Tournament} from "../model/tournament.model";
import {Action, ActionReducerMap, createFeatureSelector, createSelector} from "@ngrx/store";

export const TOGGLE_TOURNAMENT = 'TOGGLE_TOURNAMENT';

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

export function tournamentReducer(state: TournamentState = initialState, action: any): TournamentState {
  switch(action.type) {
    case TOGGLE_TOURNAMENT:
      return { tournament: action.payload };
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
