package com.resync.auth.config;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.HOTP;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OTPGenerationConfig {
  @Bean
  public HOTP HOTPConfiguration() {
    byte[] secret = SecretGenerator.generate(512);
    HOTP.Builder builder = new HOTP.Builder(secret);
    return builder.withPasswordLength(6).withAlgorithm(HMACAlgorithm.SHA256).build();
  }

  @Bean
  public TOTP TOTPConfiguration() {
    byte[] secret = SecretGenerator.generate(512);
    TOTP.Builder builder = new TOTP.Builder(secret);
    builder
        .withPasswordLength(6)
        .withAlgorithm(HMACAlgorithm.SHA512) // SHA256 and SHA512 are also supported
        .withPeriod(Duration.ofSeconds(30));

    return builder.build();
  }
}
