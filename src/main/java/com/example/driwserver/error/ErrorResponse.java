package com.example.driwserver.error;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private final int errorCode;
  private final String errorName;
  private final String errorDescription;

  private ErrorResponse(HttpStatus status, String errorDescription) {
    this.errorCode = status.value();
    this.errorName = status.getReasonPhrase();
    this.errorDescription = errorDescription;
  }

  public static ErrorResponse forError(HttpStatus status, String description) {
    return new ErrorResponse(status, description);
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorName() {
    return errorName;
  }

  public String getErrorDescription() {
    return errorDescription;
  }
}
