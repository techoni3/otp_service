package com.resync.auth.helpers;

import com.bastiaanjansen.otp.TOTP;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class TOTPGenerator implements OtpGenerator {

  private final TOTP totp;

  @Override
  public String generateOtp() {
    return totp.at(System.nanoTime());
  }
}
