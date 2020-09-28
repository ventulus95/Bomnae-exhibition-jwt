package com.ventulus95.ouathjwt.dto.artwork;

import com.ventulus95.ouathjwt.model.User;
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
    private MultipartFile file;
    private String filePath;


    @Builder
    public ArtworkSaveRequestDto(String title, String content, User user, MultipartFile file, String filePath){
        this.title = title;
        this.content = content;
        this.user = user;
        this.file = file;
        this.filePath = filePath;
    }

    public Artwork toEntity(){
        return Artwork.builder()
                .title(title)
                .content(content)
                .user(user)
                .filePath(filePath)
                .build();
    }
}
