package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.dto.comment.CommentResponseDto;
import com.ventulus95.ouathjwt.dto.comment.CommentSaveDto;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/v1/posts/{id}/comment")
    public List<CommentResponseDto> getPostByComment(@PathVariable Long id){
        return commentService.findAll(id);
    }

    @PostMapping("/api/v1/posts/{id}/comment")
    public Long save(@RequestBody CommentSaveDto dto, @PathVariable Long id, @CurrentUser UserPrincipal user) throws IOException {
        return commentService.saveComment(id, dto, user);
    }
}
