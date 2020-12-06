import {Component, OnInit} from '@angular/core';
import {FileSystemFileEntry, NgxFileDropEntry} from 'ngx-file-drop';
import {ImageService} from '../../services/image/image.service';
import {ImageUrl} from '../../domains/ImageUrl';

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

    constructor(private imageService: ImageService) {
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
            imageId: this.imageUrl.pictureId
        };
        this.imageService.watermarkImage(this.watermarkData).subscribe(
            (watermarkImageUrl) => {
                console.log(watermarkImageUrl);
                this.fileUploaded = false;
            },
            (error) => console.log(error),
            () => {
                this.choseAlgorithm = null;
                this.imageUrl = null;
            }
        );
    }
}
