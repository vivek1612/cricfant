import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private API_BASE_URL = environment.API_BASE_URL;
  constructor(private http: HttpClient) { }

  getSquads(){
    return this.http.get(this.API_BASE_URL+"/api/squads");
  }

  getSquad(squadId:number){
    return this.http.get(this.API_BASE_URL + `/api/squads/${squadId}`);
  }

  getLeague(leagueId:number){
    return this.http.get(this.API_BASE_URL + `/api/leagues/${leagueId}`);
  }

  getLeagues(tournamentId:number){
    return this.http.get(this.API_BASE_URL + `/api/leagues?tournamentId=${tournamentId}`);
  }

  getTournaments(){
    return this.http.get(this.API_BASE_URL + '/api/tournaments');
  }

  getTournament(tournamentId:number){
    return this.http.get(this.API_BASE_URL + `/api/tournaments/${tournamentId}`);
  }
}
