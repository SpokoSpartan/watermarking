import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {apiUrl} from '../../config';
import {WatermarkUrl} from '../../domains/WatermarkUrl';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageShowService {

  private imageUrl = apiUrl + 'image/';

  constructor(private httpClient: HttpClient) { }

  getImage(imageUrl: string): Observable<Blob> {
    return this.httpClient.get(imageUrl, { responseType: 'blob' });
  }

   //get Watermarked Url from Id
   public getWatermarkUrlFromId(algorithm: string, imageId: string): Observable<WatermarkUrl> {
    
    return this.httpClient.get<WatermarkUrl>(this.imageUrl + 'watermark/'+algorithm+"/"+imageId);
    
  }

}
