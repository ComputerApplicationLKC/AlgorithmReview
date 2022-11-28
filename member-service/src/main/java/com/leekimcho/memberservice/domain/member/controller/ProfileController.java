package com.leekimcho.memberservice.domain.member.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@AllArgsConstructor
@RequestMapping("/api/member-service")
@RestController
public class ProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String getProfiles() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }

}