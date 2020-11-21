export class WatermarkUrl {
  url: string;
  algorithm: string;

  constructor(url: string, algorithm: string) {
    this.url = url;
    this.algorithm = algorithm;
  }

  public getUrl(): string {
    return this.url;
  }

  public getAlgorithm(): string {
    return this.algorithm;
  }
}
