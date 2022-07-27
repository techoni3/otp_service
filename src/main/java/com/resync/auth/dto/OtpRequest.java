package com.resync.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtpRequest {

  @NotNull(message = "Missing phone number. Please enter the phone number.")
  @Size(min = 10, max = 10, message = "Please enter a valid 10 digit number")
  private String phoneNumber;

  @NotNull(message = "Missing Country Code. Please enter a valid country code")
  @Size(min = 3, max = 4, message = "Please enter a 3/4 character country code")
  private String countryCode;

  private String otp;
}
