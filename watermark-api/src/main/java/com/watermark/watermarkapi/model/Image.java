package com.watermark.watermarkapi.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "owner")
    private String owner;

    protected Image() {}

    public Image(Integer id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format(
                "Image[id=%d, name='%s', owner='%s']", id, name, owner);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
