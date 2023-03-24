package kr.co.mz.jira.support.exception;

import kr.co.mz.jira.support.code.ErrorCode;

public class BusinessException extends RuntimeException {

  private ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}