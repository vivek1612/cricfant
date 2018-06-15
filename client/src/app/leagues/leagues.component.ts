import {Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../api/api.service";
import {Observable} from "rxjs/index";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs/internal/operators";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-leagues',
  templateUrl: './leagues.component.html',
  styleUrls: ['./leagues.component.css']
})
export class LeaguesComponent implements OnInit {

  leagues: any[];
  dataSource: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns = ['name', 'tournament', 'members','points'];

  constructor(private api: ApiService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParamMap.pipe(
      switchMap(value => this.api.getLeagues(+value.get('tournamentId')))
    ).subscribe((value:any[]) => {
      this.dataSource = new MatTableDataSource(value);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
