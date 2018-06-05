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
    if (this.authService.isLoggedIn()) {
      this.router.navigateByUrl(this.returnUrl);
    }
  }

  signUp() {
    this.loggingIn = true;
    this.authService.signUp(this.email, this.password, this.name).then(r => {
      this.loginForm.resetForm();
      this.loggingIn = false;
      this.router.navigateByUrl(this.returnUrl);
    }).catch(e => {
      console.log(e);
      this.loggingIn = false;
      const err = e.code ? e.code : e.error;
      switch (err) {
        case 'auth/invalid-email':
          this.error = 'Invalid Email';
          break;
        case 'auth/email-already-in-use':
        case 'email already exists':
          this.error = 'Email in Use';
          break;
        case 'auth/weak-password':
          this.error = 'Password Too Weak';
          break;
        default:
          this.error = 'Unknown Error';
          this.authService.logout();
          break;
      }
    });
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
      const err = e.code ? e.code : e.error;
      switch (err) {
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
}
