package kr.co.mz.jira.support.exception;

import kr.co.mz.jira.support.code.ErrorCode;

public class EntitySaveFailedException extends BusinessException {

  public EntitySaveFailedException() {
    super(ErrorCode.ENTITY_SAVE_ERROR);
  }

  public EntitySaveFailedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntitySaveFailedException(String message) {
    super(message);
  }

  public EntitySaveFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
