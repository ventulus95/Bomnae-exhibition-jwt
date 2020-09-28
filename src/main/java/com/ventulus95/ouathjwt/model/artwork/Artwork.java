package com.ventulus95.ouathjwt.model.artwork;

import com.ventulus95.ouathjwt.model.BaseTimeEntity;
import com.ventulus95.ouathjwt.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Artwork extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "artwork_id")
    private Long Id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public Artwork(String title, String content, User user, String filePath){
        this.title = title;
        this.content = content;
        this.user = user;
        this.filePath = filePath;
    }

    public void update(String title, String content, String filePath){
        this.title = title;
        this.content = content;
        this.filePath = filePath;
    }
}
