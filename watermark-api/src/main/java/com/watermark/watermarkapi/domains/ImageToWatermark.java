package com.watermark.watermarkapi.domains;

public class ImageToWatermark {

    private final String url;
    private final String algorithm;

    public ImageToWatermark(String url, String algorithm) {
        this.url = url;
        this.algorithm = algorithm;
    }


    public String getUrl() {
        return url;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
