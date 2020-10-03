package com.ventulus95.ouathjwt.dto.artwork;

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
    private MultipartFile file;
    private String filePath;

    @Builder
    public ArtworkUpadteRequestDto(String title, String content, MultipartFile file, String filePath, String artist, int generation, String format){
        this.title = title;
        this.content = content;
        this.file = file;
        this.filePath = filePath;
        this.artist = artist;
        this.generation = generation;
        this.format = format;
    }
}
