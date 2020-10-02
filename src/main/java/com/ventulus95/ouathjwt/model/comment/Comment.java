package com.ventulus95.ouathjwt.model.comment;

import com.ventulus95.ouathjwt.model.BaseTimeEntity;
import com.ventulus95.ouathjwt.model.user.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commnet_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(String comment, Artwork artwork, User user){
        this.comment = comment;
        this.artwork = artwork;
        this.user = user;
    }

}
