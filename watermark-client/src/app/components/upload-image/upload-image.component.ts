import {Component, OnInit} from '@angular/core';
import {FileSystemFileEntry, NgxFileDropEntry} from 'ngx-file-drop';
import {ImageService} from '../../services/image/image.service';
import {ImageUrl} from '../../domains/ImageUrl';
import {ActivatedRoute, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
    selector: 'app-upload-image',
    templateUrl: './upload-image.component.html',
    styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {

    choseAlgorithm: string;
    uploadingInProgress = false;
    fileUploaded = false;
    imageUrl: ImageUrl;
    algorithms: string[] = ['DCT', 'LSBMR'];
    watermarkData: object;

    constructor(
        private imageService: ImageService,
        private route: ActivatedRoute, 
        private router: Router) {
    }

    ngOnInit(): void {
    }

    public dropped(files: NgxFileDropEntry[]): void {
        for (const droppedFile of files) {
            if (droppedFile.fileEntry.isFile) {
                const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
                fileEntry.file((file: File) => {

                    if (file.size > 5242880) {
                        console.log('File too big');
                    } else if (!(file.type === 'image/jpeg' || file.type === 'image/jpg')) {
                        console.log('File not supported. Only jpeg and jpg are supported!');
                    }

                    this.uploadingInProgress = true;
                    this.imageService.uploadImage(file).subscribe(
                        (imageUrl) => {
                            console.log(imageUrl);
                            this.imageUrl = imageUrl;
                            this.fileUploaded = true;
                        },
                        (error) => console.log(error),
                        () => this.uploadingInProgress = false
                    );
                });
            }
        }
    }

    public watermarkImage(): void {
          
        this.watermarkData = {
            algorithm: this.choseAlgorithm,
            imageUrl: this.imageUrl,
        };

        this.imageService.watermarkImage(this.watermarkData).subscribe(
            (watermarkImageUrl) => {
                const watermarkUrl = watermarkImageUrl.url;
                console.log(watermarkImageUrl);
                this.fileUploaded = false;
                //console.log(this.imageUrl);
                const imgUrl = this.imageUrl.url;

                console.log("1" + imgUrl);
                console.log("2" + watermarkUrl);
                this.router.navigate(['/display', {imgUrl:this.imageUrl.url, watermarkImage: watermarkImageUrl.url }]);
                //console.log("watermark" +  watermarkImageUrl.);
            },
            (error) => console.log(error),
            () => {
                this.imageService.watermarkImage(this.watermarkData);
                this.choseAlgorithm = null;
                this.imageUrl = null;
            }
        );

      
    }
}
