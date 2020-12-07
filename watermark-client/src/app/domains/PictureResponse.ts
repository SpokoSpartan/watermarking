export class PictureResponse {

    url: string;
    watermarkedUrl: string;
    pictureId: number;

    constructor(url: string, pictureId: number, watermarkedUrl: string) {
        this.url = url;
        this.pictureId = pictureId;
        this.watermarkedUrl = watermarkedUrl;
      }

  }
