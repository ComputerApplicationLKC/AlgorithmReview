package com.leekimcho.guestservice.controller;

import com.leekimcho.guestservice.client.ServiceClient;
import com.leekimcho.guestservice.dto.*;
import com.leekimcho.guestservice.entity.GuestBook;
import com.leekimcho.guestservice.mapper.GuestMapper;
import com.leekimcho.guestservice.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.leekimcho.guestservice.common.SuccessMessage.*;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guest-service/guests")
public class GuestController {

    private final GuestBookService guestBookService;
    private final GuestMapper guestMapper;
    private final ServiceClient client;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Value("${admin-email}")
    private String email;

    @GetMapping
    public ResponseEntity<?> getGuestBookList(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        List<GuestResponseDto> guestBookList = guestBookService.getGuestBookList(PageRequest.of(page, size)).stream()
                .map(guestMapper::toDto)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_GUEST_LIST, guestBookList)
        );
    }

    @GetMapping("/count")
    public ResponseEntity<?> getGuestBookCount() {
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_GUEST_COUNT, guestBookService.getGuestBookCount())
        );
    }

    @PostMapping
    public ResponseEntity<?> saveGuestBook(@RequestBody @Valid GuestRequestDto requestDto) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        MemberDto member = circuitbreaker.run(() -> client.getMemberContext(email), throwable -> new MemberDto(1L, "김승진"));
        requestDto.setMemberId(member.getMemberId());
        GuestBook guestBook = guestBookService.saveGuestBook(guestMapper.toEntity(requestDto));
        client.getMemberGuest(new ContextRequest(email, guestBook.getId()));

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_REGISTER_GUEST));
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<?> deleteGuestBook(@PathVariable Long guestId) {
        guestBookService.deleteGuestBook(guestId);
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_GUEST)
        );
    }


}
