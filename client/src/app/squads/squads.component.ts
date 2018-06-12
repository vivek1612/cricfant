import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";

@Component({
  selector: 'app-squads',
  templateUrl: './squads.component.html',
  styleUrls: ['./squads.component.css']
})
export class SquadsComponent implements OnInit {

  squads: any;

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.apiService.getSquads().subscribe(value => {
      this.squads = value;
    }), err => {
      console.log(err);
    }
  }

}
