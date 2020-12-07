export class PictureResponse {

    private url: string;
    private watermarkedUrl: string;
    private pictureId: number;

    constructor(url: string, pictureId: number, watermarkedUrl: string) {
        this.url = url;
        this.pictureId = pictureId;
        this.watermarkedUrl = watermarkedUrl;
      }
   
  }