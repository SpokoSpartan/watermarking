package com.watermark.watermarkapi.repositories;

import com.watermark.watermarkapi.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    List<Picture> findByName(String name);
    @Query(value = "SELECT * FROM pictures p WHERE p.picture_url = :pictureUrl ORDER BY p.picture_id DESC LIMIT 1",
            nativeQuery = true)
    Picture findByPictureUrl(String pictureUrl);
    @Query(value = "SELECT * FROM pictures p WHERE p.picture_id = :imageId",
            nativeQuery = true)
    Picture findByPictureId(Integer imageId);
}
