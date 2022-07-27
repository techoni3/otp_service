package com.resync.auth.dto;

import lombok.Data;

@Data
public class OtpResponse {
  private boolean status;

  private String message;

  public OtpResponse(boolean status, String message) {
    this.status = status;
    this.message = message;
  }
}
