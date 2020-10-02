package com.ventulus95.ouathjwt.model.guestbook;

import com.ventulus95.ouathjwt.model.BaseTimeEntity;
import com.ventulus95.ouathjwt.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Guestbook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="guestbook_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String gusetBook;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Guestbook(String gusetBook, User user){
        this.gusetBook = gusetBook;
        this.user = user;
    }
}
