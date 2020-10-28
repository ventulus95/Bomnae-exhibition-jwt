package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.exif.Exif;
import com.ventulus95.ouathjwt.model.user.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class ArtworkSaveRequestDto {
    private String title;
    private String content;
    private User user;
    private String artist;
    private int generation;
    private String format;
    private MultipartFile file;
    private String filePath;
    private Exif exif;


    @Builder
    public ArtworkSaveRequestDto(String title, String content, User user, MultipartFile file, String filePath, String artist, int generation, String format,Exif exif){
        this.title = title;
        this.content = content;
        this.user = user;
        this.file = file;
        this.filePath = filePath;
        this.artist = artist;
        this.generation = generation;
        this.format = format;
        this.exif = exif;
    }

    public Artwork toEntity(){
        return Artwork.builder()
                .title(title)
                .content(content)
                .user(user)
                .filePath(filePath)
                .artist(artist)
                .generation(generation)
                .format(format)
                .exif(exif)
                .build();
    }
}
