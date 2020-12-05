package com.watermark.watermarkapi.services;

import com.watermark.watermarkapi.domains.WatermarkUrl;
import com.watermark.watermarkapi.entities.Picture;
import com.watermark.watermarkapi.repositories.PictureRepository;
import org.springframework.stereotype.Service;


@Service
public class WatermarkService {

    private final PictureRepository pictureRepository;

    public WatermarkService(PictureRepository pictureRepository, UserService userService) {
        this.pictureRepository = pictureRepository;
    }

    public WatermarkUrl watermarkImage(Integer imageId, String algorithm) {

        Picture pictureFromDB = pictureRepository.findByPictureId(imageId);
        System.out.println(pictureFromDB);
        pictureFromDB.setWatermarkUrl(algorithm);
        pictureRepository.save(pictureFromDB);

        return new WatermarkUrl(imageId, algorithm);
    }
}
