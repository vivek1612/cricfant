import {Points} from "./points.model";

export interface Player {
  id: number;
  name: string;
  shortName: string;
  powerType: string,
  points: Points,
  skill: string
}
