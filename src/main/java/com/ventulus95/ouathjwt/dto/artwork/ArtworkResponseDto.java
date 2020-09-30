package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.artwork.Artwork;
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

    public ArtworkResponseDto(Artwork entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.filepath = entity.getFilePath();
        this.artist = entity.getArtist();
        this.generation = entity.getGeneration();
        this.format = entity.getFormat();
    }
}
