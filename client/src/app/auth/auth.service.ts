import {Inject, Injectable} from '@angular/core';

import {AngularFireAuth} from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import {Observable} from 'rxjs/index';
import {HttpClient} from '@angular/common/http';
import {User} from '../models/user.model';
import GoogleAuthProvider = firebase.auth.GoogleAuthProvider;


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user$: Observable<firebase.User>;

  private API_CURRENT_USER = '/auth/user';
  private currentUser: User | undefined;

  constructor(private firebaseAuth: AngularFireAuth,
              private http: HttpClient,
              @Inject('BASE_URL') private baseUrl: string) {
    this.user$ = firebaseAuth.authState;
  }

  signup(email: string, password: string) {
    return this.firebaseAuth
      .auth
      .createUserWithEmailAndPassword(email, password);
  }

  login(email: string, password: string) {
    return new Promise((resolve, reject) => {
      this.firebaseAuth
        .auth
        .signInWithEmailAndPassword(email, password).then(value => {
        console.log(value);
        value.user.getIdToken().then(token => {
          this.setAuthToken(token);
          this.http.get<User>(this.baseUrl + this.API_CURRENT_USER).subscribe(
            user => {
              this.currentUser = user;
              console.log(this.currentUser);
              resolve(this.currentUser);
            },
            error => { // api login fail
              console.log(error);
              reject(error);
            }
          );
        }).catch(reason => { // idToken fail
          reject(reason);
        });
      }).catch(reason => { // firebase login fail
        reject(reason);
      });
    });
  }

  loginWithGoogle() {
    return this.firebaseAuth.auth.signInWithPopup(new GoogleAuthProvider());
  }

  logout() {
    return this.firebaseAuth
      .auth
      .signOut().then(value => {
        this.removeAuthToken();
        this.currentUser = null;
      });
  }

  private removeAuthToken() {
    localStorage.removeItem('authToken');
  }

  private setAuthToken(authToken: string) {
    localStorage.setItem('authToken', authToken);
  }

  getAuthToken() {
    return localStorage.getItem('authToken');
  }

  isLoggedIn() {
    return this.getAuthToken() ? true : false;
  }
}
