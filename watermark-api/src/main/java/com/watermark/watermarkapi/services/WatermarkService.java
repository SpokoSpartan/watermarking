package com.watermark.watermarkapi.services;

import com.watermark.watermarkapi.domains.WatermarkUrl;
import com.watermark.watermarkapi.entities.Picture;
import com.watermark.watermarkapi.repositories.PictureRepository;
import org.springframework.stereotype.Service;


@Service
public class WatermarkService {

    private final PictureRepository pictureRepository;
    private final AlgorithmCommunicationClientService algorithmService;

    public WatermarkService(PictureRepository pictureRepository, AlgorithmCommunicationClientService algorithmService) {
        this.pictureRepository = pictureRepository;
        this.algorithmService = algorithmService;
    }

    public WatermarkUrl watermarkImage(Integer imageId, String algorithm) {

        Picture pictureFromDB = pictureRepository.findByPictureId(imageId);
        System.out.println(pictureFromDB);

        String watermarkedUrl = algorithmService.getWatermarkedImage(pictureFromDB.getPictureUrl(), algorithm);

        pictureFromDB.setWatermarkUrl(watermarkedUrl);
        pictureFromDB.setAlgorithm(algorithm);
        pictureRepository.save(pictureFromDB);

        return new WatermarkUrl(imageId, algorithm);
    }

}
