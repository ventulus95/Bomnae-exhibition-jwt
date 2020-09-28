package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import lombok.Getter;

@Getter
public class ArtworkResponseDto {

    private Long id;
    private User user;
    private String title;
    private String content;
    private String filepath;

    public ArtworkResponseDto(Artwork entity){
        this.id = entity.getId();
        this.user = entity.getUser();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.filepath = entity.getFilePath();
    }
}
