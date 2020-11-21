package com.watermark.watermarkapi.domains;

public class WatermarkUrl {

    private final String url;
    private final String algorithm;

    public WatermarkUrl(String url, String algorithm) {
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
