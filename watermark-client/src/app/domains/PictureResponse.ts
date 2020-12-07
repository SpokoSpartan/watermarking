export class PictureResponse {

    private _url: string;

    public getUrl(): string {
        return this._url;
    }
    public setUrl(value: string) {
        this._url = value;
    }

    private _watermarkedUrl: string;

    public getWatermarkedUrl(): string {
        return this._watermarkedUrl;
    }
    public setWatermarkedUrl(value: string) {
        this._watermarkedUrl = value;
    }

    private _pictureId: number;

    public getPictureId(): number {
        return this._pictureId;
    }
    public setPictureId(value: number) {
        this._pictureId = value;
    }
  
    constructor(url: string, pictureId: number, watermarkedUrl: string) {
        this._url = url;
        this._pictureId = pictureId;
        this._watermarkedUrl = watermarkedUrl;
      }
   
  }