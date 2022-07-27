package com.resync.auth.helpers;

import com.bastiaanjansen.otp.HOTP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class HOTPGenerator implements OtpGenerator {

  private final HOTP hotp;

  @Override
  public String generateOtp() {
    return hotp.generate(ThreadLocalRandom.current().nextInt(10000));
  }
}
