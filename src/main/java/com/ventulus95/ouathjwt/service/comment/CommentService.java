package com.ventulus95.ouathjwt.service.comment;

import com.ventulus95.ouathjwt.dto.comment.CommentResponseDto;
import com.ventulus95.ouathjwt.dto.comment.CommentSaveDto;
import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import com.ventulus95.ouathjwt.model.artwork.ArtworkRepository;
import com.ventulus95.ouathjwt.model.comment.CommentRepository;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArtworkRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Long saveComment(Long id, CommentSaveDto dto, @CurrentUser UserPrincipal user){
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        Optional<Artwork> artwork = postRepository.findById(id);
        dto.setUser(user1.get());
        dto.setArtwork(artwork.get());
        return commentRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(Long id){
        Optional<Artwork> artwork = postRepository.findById(id);
        return commentRepository.findAllByArtwork(artwork.get())
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }


}
