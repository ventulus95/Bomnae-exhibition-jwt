package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.exif.Exif;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class ArtworkUpadteRequestDto {

    public String title;
    public String content;
    private String artist;
    private int generation;
    private String format;
    private Exif exif;

    @Builder
    public ArtworkUpadteRequestDto(String title, String content, String artist, int generation, String format, Exif exif){
        this.title = title;
        this.content = content;
        this.artist = artist;
        this.generation = generation;
        this.format = format;
        this.exif = exif;
    }
}
