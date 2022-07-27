package com.resync.auth.services;

import com.resync.auth.models.User;

public interface OtpDeliveryService {
  boolean sendOTP(User user, String otp);
}
