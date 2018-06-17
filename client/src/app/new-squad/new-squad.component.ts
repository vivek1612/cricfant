import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../api/api.service';
import { Observable } from 'rxjs';
import { WaitService } from '../wait.service';
import { MatTableDataSource, MatSort, MatButtonToggleGroup, MatButtonToggle } from '@angular/material';
import { distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-new-squad',
  templateUrl: './new-squad.component.html',
  styleUrls: ['./new-squad.component.css']
})
export class NewSquadComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  tournamentFormGroup: FormGroup;
  tournaments$: Observable<any>;
  selectedTournament: any;
  upcomingMatches: any;
  players: any;
  error: string;
  displayedPlayerColumns = ['skill', 'name', 'price', 'team', 'totalPoints'];
  tournamentWS: WaitService;
  upcomingMatchesWS: WaitService;
  dataSource: MatTableDataSource<any>;
  selectedMatches: number[] = [];

  skillMap = {
    'BATSMAN': 'a',
    'BOWLER': 'b',
    'ALL_ROUNDER': 'c',
    'KEEPER': 'd'
  };

  powerMap = {
    'BATSMAN': 'h',
    'BOWLER': 'i'
  };

  constructor(private _formBuilder: FormBuilder,
    private api: ApiService) {
    this.dataSource = new MatTableDataSource([]);
    this.dataSource.filterPredicate = (data, filter: string) => {
      const accumulator = (currentTerm, key) => {
        return key === 'team' ? currentTerm + data.team.shortName : currentTerm + data[key];
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
    this.dataSource.sortingDataAccessor = (data: any, sortHeaderId: string): string => {
      return sortHeaderId === 'team' ? data.team.shortName : data[sortHeaderId];
    };
  }

  ngOnInit() {

    this.tournamentWS = new WaitService();
    this.upcomingMatchesWS = new WaitService();

    this.tournaments$ = this.api.getTournaments();

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.tournamentFormGroup = this._formBuilder.group({
      tournamentCtrl: ['', Validators.required]
    });
  }

  tournamentSelected() {
    this.api.getPlayers(this.selectedTournament).subscribe(
      value => {
        this.players = value;
        this.dataSource.data = this.players;
        this.tournamentWS.toggle();
      },
      error => { this.error = error; this.tournamentWS.toggle(); }
    );

    this.api.getUpcomingMatches(this.selectedTournament).subscribe(
      value => {
        this.upcomingMatches = (<any[]>value).slice(0, 3);
        this.upcomingMatchesWS.toggle();
      }
    );
  }

  filterByMatches(newSelectedMatches: number[]) {
    if (!this.isEqual(newSelectedMatches, this.selectedMatches)) {
      this.selectedMatches = newSelectedMatches;
      const selectedMatchTeamIds: number[] = [];
      this.selectedMatches.forEach(index => {
        selectedMatchTeamIds.push(this.upcomingMatches[index].teams[0].id);
        selectedMatchTeamIds.push(this.upcomingMatches[index].teams[1].id);
      });
      if (selectedMatchTeamIds.length > 0) {
        this.dataSource.data = this.players.filter(player => selectedMatchTeamIds.includes(player.team.id));
      } else {
        this.dataSource.data = this.players;
      }
    }
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  getSkillIcon(skill: string): string {
    let icon = skill;
    switch (skill) {
      case 'BATSMAN':
        icon = this.skillMap.BATSMAN;
        break;
      case 'BOWLER':
        icon = this.skillMap.BOWLER;
        break;
      case 'ALL_ROUNDER':
        icon = this.skillMap.ALL_ROUNDER;
        break;
      case 'KEEPER':
        icon = this.skillMap.KEEPER;
        break;
    }
    return icon;
  }

  @ViewChild(MatSort) set content(content: MatSort) {
    this.dataSource.sort = content;
  }

  isEqual(a: any[], b: any[]): boolean {
    if (a.length !== b.length) {
      return false;
    }
    for (let i = 0; i < a.length; i++) {
      if (a[i] !== b[i]) {
        return false;
      }
    }
    return true;
  }

  clearFilterByMatches(): any {
    this.dataSource.data = this.players;
  }

  @ViewChild(MatButtonToggleGroup) set buttons(content: MatButtonToggleGroup) {
    if (content) {
      content.change.pipe(distinctUntilChanged((a, b) => {
        return (a.source && b.source) ? a.source.id === b.source.id : true;
      })).subscribe(event => {
        if (event.source) {
          const selectedButtons: MatButtonToggle | MatButtonToggle[] = event.source.buttonToggleGroup.selected;
          if (selectedButtons) {
            const newSelectedMatches = [];
            if (selectedButtons instanceof MatButtonToggle) {
              const btn: MatButtonToggle = selectedButtons;
              newSelectedMatches.push(+btn.id);
            } else {
              const btns: MatButtonToggle[] = selectedButtons;
              this.selectedMatches = [];
              btns.forEach(btn => {
                newSelectedMatches.push(+btn.id);
              });
            }
            this.filterByMatches(newSelectedMatches);
          }
        } else {
          this.clearFilterByMatches();
        }
      });
    }
  }
}
