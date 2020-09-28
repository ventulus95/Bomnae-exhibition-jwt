package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.dto.artwork.ArtworkResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkUpadteRequestDto;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.artwork.ArtworkService;
import com.ventulus95.ouathjwt.service.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ArtworkApiController {

    private final ArtworkService artworkService;
    private final S3Service s3Service;

    @PostMapping("/api/v1/posts")
    public Long save(ArtworkSaveRequestDto dto, @CurrentUser UserPrincipal user) throws IOException {
        String imgpath  = s3Service.upload(dto.getFile());
        dto.setFilePath(imgpath);
        return artworkService.save(dto, user);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArtworkUpadteRequestDto dto ){
        return artworkService.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public ArtworkResponseDto findById(@PathVariable Long id, @CurrentUser UserPrincipal user){
        return artworkService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long postDelete(@PathVariable Long id){
        artworkService.delete(id);
        return id;
    }
}
