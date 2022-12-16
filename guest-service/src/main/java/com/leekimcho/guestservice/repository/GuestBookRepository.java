package com.leekimcho.guestservice.repository;

import com.leekimcho.guestservice.entity.GuestBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    List<GuestBook> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Optional<GuestBook> findByMemberId(Long memberId);
}