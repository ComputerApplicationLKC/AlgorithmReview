package com.leekimcho.guestservice.controller;

import com.leekimcho.guestservice.dto.GuestRequestDto;
import com.leekimcho.guestservice.dto.GuestResponseDto;
import com.leekimcho.guestservice.dto.ResponseDto;
import com.leekimcho.guestservice.mapper.GuestMapper;
import com.leekimcho.guestservice.service.GuestBookService;
import com.leekimcho.guestservice.utils.auth.AuthCheck;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestBookService guestBookService;
    private final GuestMapper guestMapper;

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
        guestBookService.saveGuestBook(guestMapper.toEntity(requestDto));
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_REGISTER_GUEST)
        );
    }

    @AuthCheck
    @DeleteMapping("/{guestId}")
    public ResponseEntity<?> deleteGuestBook(@PathVariable Long guestId) {
        guestBookService.deleteGuestBook(guestId);
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_GUEST)
        );
    }


}
