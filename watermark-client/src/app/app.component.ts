import {Component} from '@angular/core';
import {LoginService} from './services/login/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'watermark-client';

  isLoggedIn = false;
  email = '';

  constructor(private loginService: LoginService,
              private router: Router) {
    this.loginService.getEmail().subscribe(
      (email) => this.email = email
    );
    this.loginService.getIsLoggedIn().subscribe(
      (isLoggedIn) => this.isLoggedIn = isLoggedIn
    );
  }

  public logout(): void {
    this.loginService.logout().subscribe(
      () => this.router.navigateByUrl('login')
    );
  }

}
