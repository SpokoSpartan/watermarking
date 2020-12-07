import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login/login.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  loginParams: FormGroup;
  loginFailed = false;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.initFormGroup();
  }

  private initFormGroup(): void {
    this.loginParams = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  public login(): void {
    const email: string = this.loginParams.value.email;
    const password: string = this.loginParams.value.password;
    this.loginService.login(email, password).subscribe(
      () => {
        console.log('Success!');
        this.router.navigateByUrl('watermark');
     },
      (error) => {
        console.log(error.status);
        this.loginFailed = true;
      });
  }

}
