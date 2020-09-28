package com.ventulus95.ouathjwt.service.artwork;

import com.ventulus95.ouathjwt.dto.artwork.ArtworkListResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkResponseDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkUpadteRequestDto;
import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import com.ventulus95.ouathjwt.model.artwork.ArtworkRepository;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;

    public Long save(ArtworkSaveRequestDto dto, @CurrentUser UserPrincipal user) {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        return artworkRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ArtworkUpadteRequestDto dto) {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        artwork.update(dto.getTitle(), dto.getContent(), dto.getTitle());
        return id;
    }

    public ArtworkResponseDto findById(Long id) {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        return new ArtworkResponseDto(artwork);
    }

    @Transactional(readOnly = true)
    public List<ArtworkListResponseDto> findAllDesc(){
        return artworkRepository.findAll().stream()
                .map(ArtworkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        artworkRepository.deleteById(id);

    }

}
