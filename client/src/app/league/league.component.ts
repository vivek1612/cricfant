import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";
import {ActivatedRoute} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs/index";

@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {

  league: any;
  loaded$: Observable<any>;
  _loaded$: BehaviorSubject<any>;
  error: string;
  displayedColumns = ['rank','name','points'];

  constructor(private api: ApiService, private route: ActivatedRoute) {
    this._loaded$ = new BehaviorSubject(null);
    this.loaded$ = this._loaded$.asObservable();
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const leagueId = +params.get("id");
      this.api.getLeague(leagueId).subscribe(value => {
        this.league = value;
        console.log(this.league);
        this._loaded$.next(value);
      }, error => {
        console.log(error);
        this._loaded$.next(error);
        this.error = error.error;
      });
    });
  }

}
