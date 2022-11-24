package com.leekimcho.memberservice.domain.jwt.repository;

import com.leekimcho.memberservice.domain.jwt.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}
