import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {MaterialModule} from './material.module';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NavigationComponent} from './navigation/navigation.component';
import {LayoutModule} from '@angular/cdk/layout';
import {SquadsComponent} from './squads/squads.component';
import {LeaguesComponent} from './leagues/leagues.component';
import {TournamentsComponent} from './tournaments/tournaments.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthGuard} from './auth/auth.guard';
import { AccountOpsComponent } from './account-ops/account-ops.component';
import { HomeComponent } from './home/home.component';
import {AuthService} from "./auth/auth.service";
import {AuthInterceptor} from "./auth/auth.interceptor";
import { LeagueComponent } from './league/league.component';
import { SquadComponent } from './squad/squad.component';
import { NewSquadComponent } from './new-squad/new-squad.component';
import { TournamentComponent } from './tournament/tournament.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationComponent,
    SquadsComponent,
    LeaguesComponent,
    TournamentsComponent,
    AccountOpsComponent,
    HomeComponent,
    LeagueComponent,
    SquadComponent,
    NewSquadComponent,
    TournamentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    LayoutModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'home', component: HomeComponent},
      {path: 'squads', component: SquadsComponent, canActivate: [AuthGuard]},
      {path: 'squads/new', component: NewSquadComponent, canActivate: [AuthGuard]},
      {path: 'squads/:id', component: SquadComponent, canActivate: [AuthGuard]},
      {path: 'leagues', component: LeaguesComponent, canActivate: [AuthGuard]},
      {path: 'leagues/:id', component: LeagueComponent, canActivate: [AuthGuard]},
      {path: 'tournaments', component: TournamentsComponent, canActivate: [AuthGuard]},
      {path: 'tournaments/:id', component: TournamentComponent, canActivate: [AuthGuard]},
      {path: '', redirectTo: 'home', pathMatch: 'full'}
    ]),
  ],
  providers: [
    AuthGuard,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}


