package com.ventulus95.ouathjwt.controller;

import com.drew.imaging.ImageProcessingException;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkListResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkUpadteRequestDto;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.artwork.ArtworkService;
import com.ventulus95.ouathjwt.service.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @GetMapping("/format/{name}")
    public List<ArtworkListResponseDto> ListArtworkByFormat(@PathVariable String name){
        return artworkService.findAllFormatByName(name);
    }

    @GetMapping("/gen")
    public List<ArtworkListResponseDto> ListGeneration(){
        return artworkService.findAllGen();
    }

    @GetMapping("/gen/{id}")
    public List<ArtworkListResponseDto> ListGenerationID(@PathVariable Integer id){
        return artworkService.findAllGenById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Long save(ArtworkSaveRequestDto dto, @CurrentUser UserPrincipal user) throws IOException, ImageProcessingException {
        String imgpath  = s3Service.upload(dto.getFilePath(), dto.getFile());
        dto.setFilePath(imgpath);
        return artworkService.save(dto, user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, ArtworkUpadteRequestDto dto ) throws IOException {
        String imgpath  = s3Service.upload(dto.getFilePath(), dto.getFile());
        dto.setFilePath(imgpath);
        return artworkService.update(id, dto);
    }

    @GetMapping("/{id}")
    public ArtworkResponseDto findById(@PathVariable Long id){
        return artworkService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Long artworkDelete(@PathVariable Long id) throws UnsupportedEncodingException {
        String filePath = artworkService.delete(id);
        s3Service.deleteFile(filePath);
        return id;
    }


}
