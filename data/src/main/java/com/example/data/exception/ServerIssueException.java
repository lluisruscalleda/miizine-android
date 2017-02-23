package com.example.data.exception;

public class ServerIssueException extends Exception {

  public ServerIssueException() {
    super();
  }

  public ServerIssueException(final String message) {
    super(message);
  }

  public ServerIssueException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ServerIssueException(final Throwable cause) {
    super(cause);
  }
}
