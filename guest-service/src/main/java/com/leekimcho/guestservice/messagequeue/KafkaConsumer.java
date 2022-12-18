package com.leekimcho.guestservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leekimcho.guestservice.entity.GuestBook;
import com.leekimcho.guestservice.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 김승진 작성
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final GuestBookRepository repository;

    @Transactional
    @KafkaListener(topics = "guest-topic")
    public void updateMember(String kafkaMessage) {
        log.info("kafka Message -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer intMemberId = (Integer)map.get("memberId");
        String username = (String)map.get("username");
        Integer guestId = (Integer)map.get("id");
        Long memberId = intMemberId.longValue();

        List<GuestBook> guestBook = repository.findAllByNickname(username);
        guestBook.stream().forEach(guest -> {
            guest.setMemberId(memberId);
            repository.save(guest);
        });

    }

}
