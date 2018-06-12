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
      console.log('already logged in. navigating to '+this.returnUrl)
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
      const err = e.error;
      switch (err) {
        case 'email already exists':
          this.error = 'Email in Use';
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
      const err = e.error.error_description;
      switch (err) {
        case 'Bad credentials':
          this.error = 'Invalid Credentials';
          break;
        default:
          this.error = 'Unknown Error';
          this.authService.logout();
          break;
      }
    });
  }

  loginWithGoogle() {
    // this.loggingIn = true;
    // this.authService.loginWithGoogle().then(r => {
    //   this.loggingIn = false;
    //   this.loginForm.resetForm();
    // }).catch(e => {
    //   console.log(e);
    //   this.loggingIn = false;
    // });
  }
}
