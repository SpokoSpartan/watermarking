import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {apiUrl} from '../../config';
import {Observable} from 'rxjs';
import {ImageUrl} from '../../domains/ImageUrl';
import {WatermarkUrl} from '../../domains/WatermarkUrl';

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

  public watermarkImage(data: any): Observable<WatermarkUrl> {
    const watermarkData = new FormData();
    if (data.hasOwnProperty('algorithm')) {
      watermarkData.append('algorithm', data.algorithm);
    }
    if (data.hasOwnProperty('imageUrl')) {
      watermarkData.append('imageUrl', data.imageUrl);
    }
    return this.http.post<WatermarkUrl>(this.imageUrl + 'watermark', watermarkData);
  }
}
