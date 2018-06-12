import {Injectable, OnInit} from '@angular/core';

import {BehaviorSubject, Observable} from 'rxjs/index';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/user.model';
import {JwtHelperService} from "@auth0/angular-jwt";
import {environment} from "../../environments/environment";


@Injectable({providedIn: 'root'})
export class AuthService implements OnInit {

  user$: Observable<User>;
  private _user$: BehaviorSubject<User>;

  private API_BASE_URL = environment.API_BASE_URL;
  private API_USERS = '/api/users';
  private API_LOGIN = '/oauth/token';
  private TOKEN_AUTH_USERNAME = 'testjwtclientid';
  private TOKEN_AUTH_PASSWORD = 'XY7kmzoNzl100';

  private jwtHelper: JwtHelperService;

  constructor(private http: HttpClient) {
    this.jwtHelper = new JwtHelperService();
    this._user$ = new BehaviorSubject(this.getLocalLoggedInUser());
    this.user$ = this._user$.asObservable();
  }

  ngOnInit(): void {
    console.log("authService init");
    const accessToken = this.getAccessToken();
    if (accessToken && this.jwtHelper.isTokenExpired(accessToken)) {
      this.refreshToken();
    } else {
      const ttl = this.jwtHelper.getTokenExpirationDate(accessToken).getTime() - Date.now();
      console.log(`access token expires in ${ttl} ms`);
      if (ttl < 20000) {
        this.refreshToken();
      } else {
        setTimeout(() => this.refreshToken(), ttl - 20000);
      }
    }

  }

  private getLocalLoggedInUser(): User {
    const authToken = this.getAccessToken();
    if (authToken) {
      const token = this.jwtHelper.decodeToken(authToken);
      const user: User = token.user;
      return user;
    }
    return null;
  }

  public signUp(email: string, password: string, name: string): Promise<User> {
    return new Promise((resolve, reject) => {
      this.http.post<User>(this.API_BASE_URL + this.API_USERS, {
        "email": email,
        "password": password,
        "name": name
      }).subscribe(user => {
        console.log("signed up: " + user.email);
        this.login(email, password).then(value => {
          resolve(value);
        }).catch(reason => {
          console.log(reason);
          reject(reason);
        })
      }, error => { // api sign up fail
        console.log(error);
        reject(error);
      })
    });
  }

  public login(email: string, password: string): Promise<User> {
    return new Promise((resolve, reject) => {
      const body = `username=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}&grant_type=password`;
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')
        .set('Authorization', 'Basic ' + btoa(this.TOKEN_AUTH_USERNAME + ':' + this.TOKEN_AUTH_PASSWORD));
      this.http.post<any>(this.API_BASE_URL + this.API_LOGIN, body, {headers: headers}).subscribe(
        res => {
          console.log(res);
          this.setAccessToken(res.access_token);
          this.setRefreshToken(res.refresh_token);
          const ttl = this.jwtHelper.getTokenExpirationDate(res.access_token).getTime() - Date.now();
          console.log(`access token expires in ${ttl} ms`);
          setTimeout(() => this.refreshToken(), ttl - 20000);
          const tokenInfo = this.jwtHelper.decodeToken(res.access_token);
          this._user$.next(tokenInfo.user);
          resolve(this._user$.getValue());
        },
        error => { // api login fail
          console.log(error);
          reject(error);
        }
      );
    })
  }

  public refreshToken(): Promise<any> {
    console.log("refreshing access_token");
    return new Promise((resolve, reject) => {
      const refreshToken = this.getRefreshToken();
      if (refreshToken) {
        const body = `refresh_token=${encodeURIComponent(refreshToken)}&grant_type=refresh_token`;
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded')
          .set('Authorization', 'Basic ' + btoa(this.TOKEN_AUTH_USERNAME + ':' + this.TOKEN_AUTH_PASSWORD));
        this.http.post<any>(this.API_BASE_URL + this.API_LOGIN, body, {headers: headers}).subscribe(
          res => {
            console.log("successfully refreshed token");
            this.setAccessToken(res.access_token);
            const ttl = this.jwtHelper.getTokenExpirationDate(res.access_token).getTime() - Date.now();
            console.log(`access token expires in ${ttl} ms`);
            setTimeout(() => this.refreshToken(), ttl - 20000);
            resolve(this._user$.getValue());
          },
          error => { // api token refresh fail
            console.log(error);
            this.logout();
            reject(error);
          }
        );
      } else {
        console.log("refresh token not found")
        reject("refresh token not found");
      }
    });
  }

  public logout(): void {
    this.removeAccessToken();
    this._user$.next(null);
  }

  public isLoggedIn(): boolean {
    const token = this.getAccessToken();
    return token ? true : false;
  }

  public getAccessToken() {
    return localStorage.getItem('access_token');
  }

  public getLoggedInUser(): User {
    return this._user$.getValue();
  }

  private removeAccessToken(): void {
    localStorage.removeItem('access_token');
  }

  private setAccessToken(access_token: string): void {
    localStorage.setItem('access_token', access_token);
  }

  private setRefreshToken(refresh_token: string): void {
    localStorage.setItem('refresh_token', refresh_token);
  }

  private getRefreshToken(): string {
    return localStorage.getItem('refresh_token');
  }
}
