package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.dto.artwork.ArtworkListResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkUpadteRequestDto;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.artwork.ArtworkService;
import com.ventulus95.ouathjwt.service.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/artwork")
public class ArtworkApiController {

    private final ArtworkService artworkService;
    private final S3Service s3Service;

    @GetMapping("/random")
    public List<ArtworkListResponseDto> ListArtworkRadom(){
        return artworkService.findAllRandom();
    }

    @GetMapping("/format")
    public List<ArtworkListResponseDto> ListArtwork(){
        return artworkService.findAllFormat();
    }

    @GetMapping("/gen")
    public List<ArtworkListResponseDto> ListGeneration(){
        return artworkService.findAllGen();
    }

    @PostAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Long save(ArtworkSaveRequestDto dto, @CurrentUser UserPrincipal user) throws IOException {
        String imgpath  = s3Service.upload(dto.getFile());
        dto.setFilePath(imgpath);
        return artworkService.save(dto, user);
    }

    @PostAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArtworkUpadteRequestDto dto ){
        return artworkService.update(id, dto);
    }

    @GetMapping("/{id}")
    public ArtworkResponseDto findById(@PathVariable Long id, @CurrentUser UserPrincipal user){
        return artworkService.findById(id);
    }

    @PostAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Long artworkDelete(@PathVariable Long id){
        artworkService.delete(id);
        return id;
    }
}
