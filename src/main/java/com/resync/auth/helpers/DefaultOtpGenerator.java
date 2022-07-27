package com.resync.auth.helpers;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class DefaultOtpGenerator implements OtpGenerator {

  @Override
  public String generateOtp() {
    return String.format("%06d", ThreadLocalRandom.current().nextInt(0, 999999));
  }
}
