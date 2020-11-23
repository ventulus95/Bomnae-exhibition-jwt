package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.artwork.Artwork;
import com.ventulus95.ouathjwt.model.exif.Exif;
import lombok.Getter;

@Getter
public class ArtworkResponseDto {

    private Long id;
    private String title;
    private String content;
    private String filepath;
    private String artist;
    private int generation;
    private String format;
    private Exif exif;

    public ArtworkResponseDto(Artwork entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.filepath = entity.getFilePath();
        this.artist = entity.getArtist();
        this.generation = entity.getGeneration();
        this.format = entity.getFormat();
        this.exif = entity.getExif();
    }
}
