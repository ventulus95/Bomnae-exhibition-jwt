package com.ventulus95.ouathjwt.model.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookRepository extends JpaRepository<Guestbook, Integer> {

    List<Guestbook> findAllByOrderByModifiedDateDesc();
}
