package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.dto.comment.CommentResponseDto;
import com.ventulus95.ouathjwt.dto.comment.CommentSaveDto;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artwork/")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}/comment")
    public List<CommentResponseDto> getPostByComment(@PathVariable Long id){
        return commentService.findAll(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{id}/comment")
    public Long save(@RequestBody CommentSaveDto dto, @PathVariable Long id, @CurrentUser UserPrincipal user) throws IOException {
        return commentService.saveComment(id, dto, user);
    }
}
