import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { ImageShowService } from 'src/app/services/image-show/image-show.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';


@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImagesComponent implements OnInit {

  imageToShow:any;
  myURL:any;

  isImageLoading: boolean;

  // imgUrl: string = 'https://picsum.photos/200/300/?random';
   imgUrl: string;
   watermarkedImgUrl: string;
   imageId: string;

  constructor(private imageShowService: ImageShowService,  private route: ActivatedRoute, private router: Router) {

    this.imgUrl = this.route.snapshot.paramMap.get('imgUrl');
    this.imageId = this.route.snapshot.paramMap.get('imageId');
    console.log(this.imgUrl);
    console.log(this.imageId);
    
  

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
