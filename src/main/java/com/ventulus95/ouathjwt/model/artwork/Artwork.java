package com.ventulus95.ouathjwt.model.artwork;

import com.drew.metadata.Directory;
import com.ventulus95.ouathjwt.model.BaseTimeEntity;
import com.ventulus95.ouathjwt.model.exif.Exif;
import com.ventulus95.ouathjwt.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Artwork extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private Long Id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private int generation;

    //주제별, 자유주제형식으로 여러가지 형식이 나올수 도 있으므로 Enum도 고려해볼것
    @Column(nullable = false)
    private String format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exif_id")
    private Exif exif;

    @Builder
    public Artwork(String title, String content, User user, String filePath, String artist, int generation, String format, Exif exif){
        this.title = title;
        this.content = content;
        this.user = user;
        this.filePath = filePath;
        this.artist = artist;
        this.generation = generation;
        this.format = format;
        this.exif = exif;
    }

    public void update(String title, String content, String filePath, String artist, int generation, String format, Exif exif){
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.artist = artist;
        this.generation = generation;
        this.format = format;
        this.exif = exif;
    }
}
