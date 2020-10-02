package com.ventulus95.ouathjwt.dto.comment;

import com.ventulus95.ouathjwt.model.user.User;
import com.ventulus95.ouathjwt.model.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private User user;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.modifiedDate = comment.getModifiedDate();
        this.user = comment.getUser();
    }
}
