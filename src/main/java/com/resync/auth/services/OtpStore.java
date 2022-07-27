package com.resync.auth.services;

import com.resync.auth.models.User;

import java.util.Optional;

public interface OtpStore {
  void setOtp(User user, String otp);

  Optional<String> getOtp(User user);

  void removeOtp(User user);
}
