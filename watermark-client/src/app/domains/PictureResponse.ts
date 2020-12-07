export class PictureResponse {

    public url: string;
    public watermarkedUrl: string;
    public pictureId: number;

    constructor(url: string, pictureId: number, watermarkedUrl: string) {
        this.url = url;
        this.pictureId = pictureId;
        this.watermarkedUrl = watermarkedUrl;
      }
   
  }