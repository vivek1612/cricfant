import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;
  name: string;
  loggingIn: boolean;
  error: string;
  @ViewChild('loginForm')
  loginForm: NgForm;
  private returnUrl: string;

  constructor(public authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    console.log('login init');
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    if ( this.authService.isLoggedIn() ) {
      this.router.navigateByUrl(this.returnUrl);
    }
  }

  signup() {
    this.loggingIn = true;
    this.authService.signup(this.email, this.password).then(r => {
      this.loginForm.resetForm();
      this.loggingIn = false;
    }).catch(e => {
      console.log(e);
      this.loggingIn = false;
    });
    this.email = this.password = '';
  }

  login() {
    this.loggingIn = true;
    this.error = '';
    this.authService.login(this.email, this.password).then(r => {
      console.log(r);
      this.loginForm.resetForm();
      this.loggingIn = false;
      this.router.navigateByUrl(this.returnUrl);
    }).catch(e => {
      console.log(e);
      this.loggingIn = false;
      const code = e.code;
      switch (code) {
        case 'auth/invalid-email':
          this.error = 'Invalid Email';
          break;
        case 'auth/user-disabled':
          this.error = 'Account Disabled';
          break;
        case 'auth/user-not-found':
          this.error = 'User Not Found';
          break;
        case 'auth/wrong-password':
          this.error = 'Wrong Password';
          break;
        case 'auth/too-many-requests':
          this.error = 'Blocked For Unusual Activity';
          break;
        default:
          this.error = 'Unknown Error';
          this.authService.logout();
          break;
      }
    });
  }

  loginWithGoogle() {
    this.loggingIn = true;
    this.authService.loginWithGoogle().then(r => {
      this.loggingIn = false;
      this.loginForm.resetForm();
    }).catch(e => {
      console.log(e);
      this.loggingIn = false;
    });
  }

  logout() {
    this.loggingIn = true;
    this.authService.logout().then(r => {
      console.log('logged out');
      this.loggingIn = false;
    }).catch(e => {
      console.log(e);
      this.loggingIn = false;
    });
  }
}
