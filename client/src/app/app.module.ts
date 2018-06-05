import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {MaterialModule} from './material.module';
import {AngularFireModule} from 'angularfire2';
import {environment} from '../environments/environment';
import {AngularFireAuthModule} from 'angularfire2/auth';
import {LoginComponent} from './login/login.component';
import {FormsModule} from '@angular/forms';
import {NavigationComponent} from './navigation/navigation.component';
import {LayoutModule} from '@angular/cdk/layout';
import {SquadsComponent} from './squads/squads.component';
import {LeaguesComponent} from './leagues/leagues.component';
import {TournamentsComponent} from './tournaments/tournaments.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptorService} from './auth/auth-interceptor.service';
import {AuthGuard} from './auth/auth.guard';
import { AccountOpsComponent } from './account-ops/account-ops.component';
import { HomeComponent } from './home/home.component';
import {AuthService} from "./auth/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationComponent,
    SquadsComponent,
    LeaguesComponent,
    TournamentsComponent,
    AccountOpsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    LayoutModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'home', component: HomeComponent},
      {path: 'squads', component: SquadsComponent, canActivate: [AuthGuard]},
      {path: 'leagues', component: LeaguesComponent, canActivate: [AuthGuard]},
      {path: 'tournaments', component: TournamentsComponent, canActivate: [AuthGuard]},
      {path: '', redirectTo: 'home', pathMatch: 'full'}
    ])
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    },
    AuthService,
    {provide: 'BASE_URL', useValue: 'http://localhost:8080'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}


