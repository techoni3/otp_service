package com.resync.auth.exceptions;

public class OtpServiceException extends RuntimeException {
  public OtpServiceException(String message) {
    super(message);
  }

  public OtpServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
