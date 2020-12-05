import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ImageUrl} from '../../domains/ImageUrl';
import {FileSystemFileEntry, NgxFileDropEntry} from 'ngx-file-drop';
import {ImageService} from '../../services/image/image.service';

@Component({
  selector: 'app-check',
  templateUrl: './check.component.html',
  styleUrls: ['./check.component.css']
})
export class CheckComponent implements OnInit {

  uploadingInProgress = false;
  fileUploaded = false;
  imageUrl: ImageUrl;
  similarity: number = null;

  @ViewChild('uploaded')
  picture: ElementRef;

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

  public verifyImage(): void {
    this.imageService.verifyImage(this.imageUrl.url).subscribe(
      (similarity) => {
        this.similarity = similarity;
        const element = this.picture.nativeElement;
        if (similarity > 50) {
          element.style.border = '3px solid green';
        } else {
          element.style.border = '3px solid red';
        }
      },
      (error) => console.log(error));
  }

}
