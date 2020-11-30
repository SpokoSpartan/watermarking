import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { ImageShowService } from 'src/app/services/image-show/image-show.service';

@Component({
  selector: 'app-display-images',
  templateUrl: './display-images.component.html',
  styleUrls: ['./display-images.component.css']
})
export class DisplayImagesComponent implements OnInit {

  imageToShow:any;
  myURL:any;

  isImageLoading: boolean;

  imgUrl: string = 'https://picsum.photos/200/300/?random';
  watermarkedImgUrl: string =  'https://picsum.photos/200/300/?random';

  constructor(private imageShowService: ImageShowService) {
   this.getImageFromService();
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
createImageFromBlob(image: Blob) {
   let reader = new FileReader();
   reader.addEventListener("load", () => {
      this.imageToShow = reader.result;
   }, false);

   if (image) {
      reader.readAsDataURL(image);
   }
  }

  getImageFromService() {
      this.isImageLoading = true;
      this.imageShowService.getImage(this.imgUrl).subscribe(data => {
        this.createImageFromBlob(data);
        this.isImageLoading = false;
      }, error => {
        this.isImageLoading = false;
        console.log(error);
      });
  }
}
