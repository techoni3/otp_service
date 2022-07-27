package com.resync.auth.services;

import com.resync.auth.models.User;
import org.springframework.stereotype.Service;

@Service
public class OtpDeliverySmsService implements OtpDeliveryService {

  @Override
  public boolean sendOTP(User user, String otp) {
    // This method would publish an event to send the OTP via SMS
    // which would be consumed by a listener that integrates with the
    return true;
  }
}
