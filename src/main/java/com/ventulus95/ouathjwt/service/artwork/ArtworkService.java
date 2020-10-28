package com.ventulus95.ouathjwt.service.artwork;

import com.drew.imaging.ImageProcessingException;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkListResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkUpadteRequestDto;
import com.ventulus95.ouathjwt.model.exif.ExifRepository;
import com.ventulus95.ouathjwt.model.user.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import com.ventulus95.ouathjwt.model.artwork.ArtworkRepository;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.util.ExifUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;
    private final ExifRepository exifRepository;

    public Long save(ArtworkSaveRequestDto dto, @CurrentUser UserPrincipal user) throws IOException, ImageProcessingException {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        dto.setExif(exifRepository.save(ExifUtil.imgtoExif(dto.getFile())));
        return artworkRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ArtworkUpadteRequestDto dto) {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        artwork.update(dto.getTitle(), dto.getContent(), dto.getFilePath(), dto.getArtist(), dto.getGeneration(), dto.getFormat(), dto.getExif());
        return id;
    }

    public ArtworkResponseDto findById(Long id) {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        return new ArtworkResponseDto(artwork);
    }

    @Transactional(readOnly = true)
    public List<ArtworkListResponseDto> findAllRandom(){
        return artworkRepository.findAllByAOrderByRandom().stream()
                .map(ArtworkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ArtworkListResponseDto> findAllFormat(){
        return artworkRepository.findAllByOrderByFormat().stream()
                .map(ArtworkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ArtworkListResponseDto> findAllGen(){
        return artworkRepository.findAllByOrderByGeneration().stream()
                .map(ArtworkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public String delete(Long id) throws UnsupportedEncodingException {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        String filePath = artwork.getFilePath();
        filePath = filePath.replace("https://bomnae-static.s3.ap-northeast-2.amazonaws.com/", "");
        filePath = URLDecoder.decode(filePath, "UTF-8");
        artworkRepository.deleteById(id);
        return filePath;
    }

}
