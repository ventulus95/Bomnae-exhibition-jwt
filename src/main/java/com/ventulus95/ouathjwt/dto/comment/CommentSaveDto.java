package com.ventulus95.ouathjwt.dto.comment;

import com.ventulus95.ouathjwt.model.User;
import com.ventulus95.ouathjwt.model.artwork.Artwork;
import com.ventulus95.ouathjwt.model.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {

    private User user;
    private String comment;
    private Artwork artwork;


    @Builder
    public CommentSaveDto(String comment, Artwork artwork, User user){
        this.comment = comment;
        this.artwork = artwork;
        this.user = user;
    }

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .artwork(artwork)
                .user(user)
                .build();
    }

}
