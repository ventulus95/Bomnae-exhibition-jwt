package com.ventulus95.ouathjwt.model.artwork;

import com.ventulus95.ouathjwt.dto.artwork.ArtworkListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    @Query(value = "SELECT * FROM artwork ORDER BY RAND()", nativeQuery = true)
    List<Artwork> findAllByAOrderByRandom();

    List<Artwork> findAllByOrderByGeneration();

    List<Artwork> findAllByOrderByFormat();

    List<Artwork> findAllByGeneration(Integer gen);

    List<Artwork> findAllByFormatContains(String format);
}
