package com.resync.auth.services;

import com.resync.auth.dto.OtpRequest;

public interface LoginService {
  void register(OtpRequest otpRequest);

  void authenticateOtp(OtpRequest otpRequest);
}
