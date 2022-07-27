package com.resync.auth.services;

import com.resync.auth.exceptions.OtpServiceException;
import com.resync.auth.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisOtpStore implements OtpStore {

  private final long ttl;

  private final StringRedisTemplate redisTemplate;

  private final ValueOperations<String, String> valueOperations;

  public RedisOtpStore(
      StringRedisTemplate redisTemplate, @Value("${spring.redis.timeToLive}") long ttl) {
    this.ttl = ttl;
    this.redisTemplate = redisTemplate;
    this.valueOperations = redisTemplate.opsForValue();
  }

  @Override
  public void setOtp(User user, String otp) {
    try {
      String key = this.getKey(user);
      Boolean b = redisTemplate.hasKey(key);
      if (Boolean.TRUE.equals(b)) {
        // if key already exists, don't overwrite with fresh ttl
        log.info("OTP already exists for {}:{}", user.getPhoneNumber(), valueOperations.get(key));
        return;
      }
      valueOperations.set(key, otp);
      redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    } catch (RuntimeException e) {
      throw new OtpServiceException("Failed to set OTP in RedisOtpStore", e);
    }
    log.info("OTP stored successfully for {}:{}", user.getPhoneNumber(), otp);
  }

  @Override
  public Optional<String> getOtp(User user) {
    try {
      String key = this.getKey(user);
      Boolean b = redisTemplate.hasKey(key);
      if (Boolean.TRUE.equals(b)) {
        return Optional.ofNullable(valueOperations.get(key));
      } else {
        return Optional.empty();
      }
    } catch (RuntimeException e) {
      throw new OtpServiceException("Error while retrieving from the redisOtpStore:", e);
    }
  }

  @Override
  public void removeOtp(User user) {
    try {
      String key = this.getKey(user);
      redisTemplate.delete(key);
    } catch (RuntimeException e) {
      throw new OtpServiceException("Error while removing from the redisOtpStore:", e);
    }
  }

  private String getKey(User user) {
    return String.format("LOGIN_OTP::%s::%s", user.getPhoneNumber(), user.getCountryCode());
  }
}
