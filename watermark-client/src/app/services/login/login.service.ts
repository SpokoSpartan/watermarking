import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../config';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = apiUrl + 'login';
  private logoutUrl = apiUrl + 'logout';

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject(false);
  private email: BehaviorSubject<string> = new BehaviorSubject('');

  constructor(private http: HttpClient) {
  }

  public login(email: string, password: string): Observable<void> {
    const loginData = new FormData();
    loginData.append('username', email);
    loginData.append('password', password);
    const loginResponse: Observable<void> = this.http.post<void>(this.loginUrl, loginData, {withCredentials: true});
    loginResponse.subscribe(
      () => {
        this.email.next(email);
        this.loggedIn.next(true);
      },
      () => {
        this.email.next('');
        this.loggedIn.next(false);
      });
    return loginResponse;
  }

  public logout(): Observable<void> {
    const logoutResponse: Observable<void> = this.http.post<void>(this.logoutUrl, null, {withCredentials: true});
    logoutResponse.subscribe(
      () => {
        this.email.next('');
        this.loggedIn.next(false);
      });
    return logoutResponse;
  }

  public getIsLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  public getEmail(): Observable<string> {
    return this.email.asObservable();
  }

}
