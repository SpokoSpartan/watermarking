export class ImageUrl {
  url: string;
  secureUrl: string;

  constructor(url: string, secureUrl: string) {
    this.url = url;
    this.secureUrl = secureUrl;
  }

  public getUrl(): string {
    return this.url;
  }
}
