package com.ventulus95.ouathjwt.dto.artwork;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArtworkUpadteRequestDto {

    public String title;
    public String content;

    @Builder
    public ArtworkUpadteRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
