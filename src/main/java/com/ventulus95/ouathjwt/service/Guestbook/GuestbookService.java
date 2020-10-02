package com.ventulus95.ouathjwt.service.Guestbook;

import com.ventulus95.ouathjwt.dto.gusetbook.GuestBookSaveDto;
import com.ventulus95.ouathjwt.dto.gusetbook.GuestbookResponseDto;
import com.ventulus95.ouathjwt.model.user.User;
import com.ventulus95.ouathjwt.model.guestbook.GuestbookRepository;
import com.ventulus95.ouathjwt.repository.UserRepository;
import com.ventulus95.ouathjwt.security.CurrentUser;
import com.ventulus95.ouathjwt.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final UserRepository userRepository;
    private final GuestbookRepository guestbookRepository;

    public Long saveGuestBook(GuestBookSaveDto dto, @CurrentUser UserPrincipal user){
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        return guestbookRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<GuestbookResponseDto> findAllDesc(){
        return guestbookRepository.findAllByOrderByModifiedDateDesc().stream()
                .map(GuestbookResponseDto::new)
                .collect(Collectors.toList());
    }
}
