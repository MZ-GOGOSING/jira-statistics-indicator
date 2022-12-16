package kr.co.mz.jira.support.exception;

import kr.co.mz.jira.support.code.ErrorCode;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException() {
    super(ErrorCode.NO_SUCH_ENTITY_ERROR);
  }
    
  public EntityNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
