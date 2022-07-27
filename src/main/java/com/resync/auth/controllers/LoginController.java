package com.resync.auth.controllers;

import com.resync.auth.dto.OtpRequest;
import com.resync.auth.dto.OtpResponse;
import com.resync.auth.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("register")
  public ResponseEntity<OtpResponse> register(@Valid @RequestBody OtpRequest requestBody) {
    loginService.register(requestBody);
    return ResponseEntity.ok()
        .body(
            new OtpResponse(
                true,
                String.format(
                    "OTP successfully sent to phone number: %s-%s",
                    requestBody.getCountryCode(), requestBody.getPhoneNumber())));
  }

  @PostMapping("login")
  public ResponseEntity<OtpResponse> login(@RequestBody OtpRequest requestBody) {
    loginService.authenticateOtp(requestBody);
    return ResponseEntity.ok()
        .body(
            new OtpResponse(
                true,
                String.format(
                    "User Authentication successful for %s-%s.",
                    requestBody.getCountryCode(), requestBody.getPhoneNumber())));
  }
}
