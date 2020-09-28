package com.ventulus95.ouathjwt.dto.gusetbook;

import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.guestbook.Guestbook;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GuestbookResponseDto {

    private String guestbook;
    private User user;
    private LocalDateTime modifiedDate;

    public GuestbookResponseDto(Guestbook guestBook) {
        this.guestbook = guestBook.getGusetBook();
        this.user = guestBook.getUser();
        this.modifiedDate = guestBook.getModifiedDate();
    }
}
