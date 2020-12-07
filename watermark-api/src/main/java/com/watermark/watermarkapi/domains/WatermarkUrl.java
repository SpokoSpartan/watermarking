package com.watermark.watermarkapi.domains;

public class WatermarkUrl {

    private final String algorithm;
    private final Integer imageId;

    public WatermarkUrl(Integer imageId, String algorithm) {
        this.imageId = imageId;
        this.algorithm = algorithm;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
