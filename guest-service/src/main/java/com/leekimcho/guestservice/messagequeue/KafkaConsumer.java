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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final GuestBookRepository repository;

    @KafkaListener(topics = "problem-topic")
    public void updateMember(String kafkaMessage) {
        log.info("kafka Message -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Long memberId = (Long)map.get("memberId");
        String username = (String)map.get("username");

        Optional<GuestBook> guestBook = repository.findByMemberId(memberId);

        if(guestBook.isPresent()) {
            GuestBook entity = guestBook.get();
            entity.setNickname(username);
            repository.save(entity);
        }

    }

}
