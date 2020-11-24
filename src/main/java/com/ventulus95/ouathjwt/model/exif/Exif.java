package com.ventulus95.ouathjwt.model.exif;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Exif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exif_id")
    private Long id;

    @Column
    private String aperture;

    @Column
    private String focusLength;

    @Column
    private String iso;

    @Column
    private String exposureTime;

    @Column
    private String model;

    @Column
    private String maker;

    @Builder
    public Exif(String aperture, String focusLength, String iso, String exposureTime, String model, String maker){
        this.aperture = aperture;
        this.focusLength = focusLength;
        this.iso = iso;
        this.exposureTime = exposureTime;
        this.maker = maker;
        this.model = model;
    }

    public void update(String aperture, String focusLength, String iso, String exposureTime, String model, String maker){
        this.aperture = aperture;
        this.focusLength = focusLength;
        this.iso = iso;
        this.exposureTime = exposureTime;
        this.maker = maker;
        this.model = model;
    }


}
