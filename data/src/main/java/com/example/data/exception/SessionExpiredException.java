package com.example.data.exception;

public class SessionExpiredException extends Exception {

  public SessionExpiredException() {
    super();
  }

  public SessionExpiredException(final String message) {
    super(message);
  }

  public SessionExpiredException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public SessionExpiredException(final Throwable cause) {
    super(cause);
  }
}