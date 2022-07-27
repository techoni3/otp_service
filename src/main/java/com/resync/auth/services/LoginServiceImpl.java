package com.resync.auth.services;

import com.resync.auth.dto.OtpRequest;
import com.resync.auth.exceptions.OtpServiceException;
import com.resync.auth.exceptions.UserServiceException;
import com.resync.auth.helpers.OtpGenerator;
import com.resync.auth.models.User;
import com.resync.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
  private final UserRepository userRepository;

  private final OtpDeliveryService otpDeliveryService;

  private final OtpGenerator otpGenerator;

  private final OtpStore otpStore;

  @Override
  public void register(OtpRequest otpRequest) {

    // check if the user is already registered
    Optional<User> user =
        userRepository.findByPhoneNumberAndCountryCode(
            otpRequest.getPhoneNumber(), otpRequest.getCountryCode());

    // TODO: Move user registration flow to User Service
    try {
      if (user.isEmpty()) {
        // create new user if the user is not already registered
        User newUser = new User(otpRequest.getPhoneNumber(), otpRequest.getCountryCode());
        newUser = userRepository.save(newUser);
        user = Optional.of(newUser);
      }
    } catch (RuntimeException e) {
      throw new UserServiceException("Failed to create User object", e);
    }

    // generate 6 digit OTP for given user
    String otp = otpGenerator.generateOtp();
    // set the OTP in otp store and publish the event to send the sms
    try {
      otpStore.setOtp(user.get(), otp);
      otpDeliveryService.sendOTP(user.get(), otp);
    } catch (OtpServiceException e) {
      throw new UserServiceException(
          String.format("Failed to send OTP to user: %s", user.get().getId()), e);
    }
  }

  @Override
  public void authenticateOtp(OtpRequest otpRequest) {
    Optional<User> user =
        userRepository.findByPhoneNumberAndCountryCode(
            otpRequest.getPhoneNumber(), otpRequest.getCountryCode());
    if (user.isEmpty()) {
      throw new UserServiceException(
          "User not found with phone number: " + otpRequest.getPhoneNumber());
    }
    User phoneUser = user.get();
    Optional<String> storedOtp = otpStore.getOtp(phoneUser);
    boolean isAuthenticated = storedOtp.isPresent() && storedOtp.get().equals(otpRequest.getOtp());
    if (!isAuthenticated) {
      throw new UserServiceException("Invalid OTP entered. Please try again.");
    }
    otpStore.removeOtp(phoneUser);
    phoneUser.setPhoneVerified(true);
    userRepository.save(phoneUser);
  }
}
