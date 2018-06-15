import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../api/api.service";
import {switchMap} from "rxjs/operators";
import {WaitService} from "../wait.service";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.css']
})
export class TournamentComponent implements OnInit {

  tournament: any;
  error: string;
  waitService: WaitService;

  constructor(private api: ApiService, private route: ActivatedRoute) {
    this.waitService = new WaitService();
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap(value => this.api.getTournament(+value.get('id'))),
    ).subscribe(
      value => {
        this.tournament = value;
        this.waitService.toggle()
      },
      error => {
        this.error = error;
        this.waitService.toggle()
      }
    );
  }
}
