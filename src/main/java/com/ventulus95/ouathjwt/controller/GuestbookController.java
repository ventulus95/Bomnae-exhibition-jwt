package com.ventulus95.ouathjwt.controller;

import com.ventulus95.ouathjwt.dto.gusetbook.GuestBookSaveDto;
import com.ventulus95.ouathjwt.dto.gusetbook.GuestbookResponseDto;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import com.ventulus95.ouathjwt.service.Guestbook.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/guestbook-list")
    public List<GuestbookResponseDto> getPostByComment(){
        return guestbookService.findAllDesc();
    }

    @PostMapping("/guestbook")
    public Long save(@RequestBody GuestBookSaveDto dto, @CurrentUser UserPrincipal user) throws IOException {
        return guestbookService.saveGuestBook(dto, user);
    }

}
