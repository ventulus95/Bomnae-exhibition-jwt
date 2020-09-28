package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArtworkListResponseDto {

    private Long id;
    private User user;
    private String title;
    private LocalDateTime modifiedDate;

    public ArtworkListResponseDto(Artwork entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
