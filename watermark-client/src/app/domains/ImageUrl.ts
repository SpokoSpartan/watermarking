export class ImageUrl {
  url: string;
  secureUrl: string;
  userId: number;
  pictureId: number;

  constructor(url: string, secureUrl: string, userId: number, pictureId: number) {
    this.url = url;
    this.secureUrl = secureUrl;
    this.userId = userId;
    this.pictureId = pictureId;
  }

  public getUrl(): string {
    return this.url;
  }

  public getUserId(): number {
    return this.userId;
  }

  public getPictureId(): string {
    return this.url;
  }
}
