package com.leekimcho.problemservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leekimcho.problemservice.common.advice.exception.EntityNotFoundException;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.problem.repository.ProblemRepository;
import com.leekimcho.problemservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProblemRepository problemRepository;
    private final ReviewRepository reviewRepository;

    @KafkaListener(topics = "member-topic")
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
        Problem problem = problemRepository.findById((Long)map.get("problemId")).orElseThrow(EntityNotFoundException::new);

        MemberDto writer = problem.getWriter();
        writer.setMemberId(memberId);
        writer.setUsername(username);
        problemRepository.save(problem);

        problem.getReviewList().stream().forEach(review -> {
            review.getMember().setMemberId(memberId);
            review.getMember().setUsername(username);
            reviewRepository.save(review);
        });
    }

}
