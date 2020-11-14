package com.watermark.watermarkapi.repositories;

import com.watermark.watermarkapi.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    List<Picture> findByName(String name);

}
