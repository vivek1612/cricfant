import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account-ops',
  templateUrl: './account-ops.component.html',
  styleUrls: ['./account-ops.component.css']
})
export class AccountOpsComponent implements OnInit {

  logout() {
    this.authService.logout().then(value => {
      this.router.navigateByUrl('');
    });
  }

  login() {
    this.router.navigateByUrl('login');
  }

  constructor(public authService: AuthService,
              private router: Router) {
  }


  ngOnInit() {
  }

}
