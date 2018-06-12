import {Component, Input, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";
import {ActivatedRoute} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs/index";

@Component({
  selector: 'app-squad',
  templateUrl: './squad.component.html',
  styleUrls: ['./squad.component.css']
})
export class SquadComponent implements OnInit {

  @Input()
  squad: any;
  @Input()
  showPlayers = true;
  loaded$: Observable<any>;
  _loaded$: BehaviorSubject<any>;
  error: string;

  displayedLeagueColumns = ['name', 'rank'];
  displayedPlayerColumns = ['skill', 'name', 'team'];

  skillMap = {
    "BATSMAN": "a",
    "BOWLER": "b",
    "ALL_ROUNDER": "c",
    "KEEPER": "d"
  }

  powerMap = {
    "BATSMAN": "h",
    "BOWLER": "i"
  }

  constructor(private api: ApiService,
              private route: ActivatedRoute) {
    this._loaded$ = new BehaviorSubject(null);
    this.loaded$ = this._loaded$.asObservable();
  }

  ngOnInit() {
    if (!this.squad) {
      this.route.paramMap.subscribe(params => {
        this.api.getSquad(+params.get("id")).subscribe(value => {
          this.squad = value;
          console.log(value);
          this._loaded$.next(value);
        }, error => {
          console.log(error);
          this._loaded$.next(error);
        })
      }, error => {
        this.error = error.message;
        console.log(error);
        this._loaded$.next(error);
      });
    } else {
      console.log(this.squad);
      this._loaded$.next(this.squad);
    }
  }

  getSkillIcon(skill:string):string{
    let icon = skill;
    switch (skill){
      case "BATSMAN":
        icon = this.skillMap.BATSMAN;
        break;
       case "BOWLER":
         icon = this.skillMap.BOWLER;
         break;
       case "ALL_ROUNDER":
         icon = this.skillMap.ALL_ROUNDER;
         break;
        case "KEEPER":
          icon = this.skillMap.KEEPER;
          break;
    }
    return icon;
  }

}
