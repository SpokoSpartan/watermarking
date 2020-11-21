import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {apiUrl} from '../../config';
import {Observable} from 'rxjs';
import {ImageUrl} from "../../domains/ImageUrl";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private imageUrl = apiUrl + 'image/';

  constructor(private http: HttpClient) {
  }

  public uploadImage(image: File): Observable<ImageUrl> {
    const uploadData = new FormData();
    uploadData.append('image', image);
    return this.http.post<ImageUrl>(this.imageUrl + 'upload', uploadData, {withCredentials: true});
  }

}
