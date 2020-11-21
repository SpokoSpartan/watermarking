import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../config';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = apiUrl + 'login';

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string): Observable<void> {
    const loginData = new FormData();
    loginData.append('username', username);
    loginData.append('password', password);
    return this.http.post<void>(this.loginUrl, loginData, {withCredentials: true});
  }

}
