package com.ventulus95.ouathjwt.model.comment;

import com.ventulus95.ouathjwt.model.artwork.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArtwork(Artwork artwork);
}
