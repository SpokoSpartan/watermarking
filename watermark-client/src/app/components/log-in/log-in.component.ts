import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login/login.service";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
  }

  public login(): void {
    this.loginService.login('jan.nowak@example.com', 'test').subscribe(
      () => console.log('Success!'),
      (error) => console.log(error.status)
    );
  }

}
