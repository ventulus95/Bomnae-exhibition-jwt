package com.ventulus95.ouathjwt.dto.gusetbook;

import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.guestbook.Guestbook;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestBookSaveDto {

    private User user;
    private String comment;

    @Builder
    public GuestBookSaveDto(User user, String comment){
        this.user = user;
        this.comment = comment;
    }

    public Guestbook toEntity(){
        return Guestbook.builder()
                .user(user)
                .gusetBook(comment)
                .build();
    }

}
