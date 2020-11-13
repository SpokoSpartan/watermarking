package com.watermark.watermarkapi.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer picture_id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user_id;
    private String pictureUrl;
    private String name;
    private String watermarkUrl;
    private String algorithm;
    @CreationTimestamp
    @Column(columnDefinition="TIMESTAMP DEFAULT NOW()")
    private Timestamp publicationDate;

    protected Picture() {
    }

    public Picture(String pictureUrl, String name, String watermarkUrl, String algorithm) {
        this.pictureUrl = pictureUrl;
        this.name = name;
        this.watermarkUrl = watermarkUrl;
        this.algorithm = algorithm;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getName() {
        return name;
    }

    public String getWatermarkUrl() {
        return watermarkUrl;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }
    @Override
    public String toString() {
        return String.format(
                "Picture[pictureUrl='%s', name='%s', watermarkUrl='%s', algorithm='%s']", pictureUrl,
                name, watermarkUrl, algorithm);
    }
}
