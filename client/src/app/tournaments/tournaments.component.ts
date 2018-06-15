import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {ApiService} from "../api/api.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html',
  styleUrls: ['./tournaments.component.css']
})
export class TournamentsComponent implements OnInit {

  tournaments$: Observable<any>;

  constructor(private api: ApiService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.tournaments$ = this.api.getTournaments();
  }

}
