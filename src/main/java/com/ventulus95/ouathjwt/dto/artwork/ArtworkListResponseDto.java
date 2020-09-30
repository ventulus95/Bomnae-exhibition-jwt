package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.artwork.Artwork;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArtworkListResponseDto {

    private Long id;
    private String title;
    private String filePath;
    private LocalDateTime modifiedDate;

    public ArtworkListResponseDto(Artwork entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.filePath = entity.getFilePath();
        this.modifiedDate = entity.getModifiedDate();
    }
}
