package com.leekimcho.problemservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.problem.repository.ProblemRepository;
import com.leekimcho.problemservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 김승진 작성
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProblemRepository problemRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
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
        Integer intMemberId = (Integer)map.get("memberId");
        String username = (String)map.get("username");
        if (map.containsKey("id")) {
            Integer intProblemId = (Integer)map.get("id");
            Optional<Problem> oProblem = problemRepository.findById(intProblemId.longValue());

            if(oProblem.isPresent()) {
                Problem problem = oProblem.get();
                MemberDto writer = problem.getWriter();
                writer.setMemberId(intMemberId.longValue());
                writer.setUsername(username);
                problemRepository.save(problem);

                if (problem.getReviewList().isEmpty()) {
                    return;
                }

                problem.getReviewList().stream().forEach(review -> {
                    MemberDto member = review.getMember();
                    member.setMemberId(intMemberId.longValue());
                    member.setUsername(username);
                    reviewRepository.save(review);
                });
            }
        }
    }

}
