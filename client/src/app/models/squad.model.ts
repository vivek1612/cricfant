import {League} from "./league.model";
import {Player} from "./player.model";

export interface Squad {
  id: number,
  name: string,
  forMatchId: number,
  subsLeft: number,
  tournamentId: number,
  leagues: League[],
  players: Player[]
}
