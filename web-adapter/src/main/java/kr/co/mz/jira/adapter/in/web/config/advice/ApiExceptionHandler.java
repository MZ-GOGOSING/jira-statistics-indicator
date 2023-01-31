package kr.co.mz.jira.adapter.in.web.config.advice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import kr.co.mz.support.code.ErrorCode;
import kr.co.mz.support.dto.ApiResponse;
import kr.co.mz.support.dto.ApiResponseGenerator;
import kr.co.mz.support.dto.InvalidArguments;
import kr.co.mz.support.exception.BusinessException;
import kr.co.mz.support.exception.EntityDeletionFailedException;
import kr.co.mz.support.exception.EntityExistsException;
import kr.co.mz.support.exception.EntityNotFoundException;
import kr.co.mz.support.exception.EntitySaveFailedException;
import kr.co.mz.support.exception.InvalidStateException;
import kr.co.mz.support.exception.InvalidValueException;
import kr.co.mz.support.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

	/**
	 * Business Exception
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(BusinessException.class)
	public ApiResponse<Void> handleWaning(final BusinessException e) {
		return handleBusinessException(e, false);
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({
		InvalidValueException.class,
		InvalidStateException.class,
		EntityNotFoundException.class,
		EntityExistsException.class,
		EntitySaveFailedException.class,
		EntityDeletionFailedException.class
	})
	public ApiResponse<Void> handleDataException(final BusinessException e) {
		return handleBusinessException(e, false);
	}

	private ApiResponse<Void> handleBusinessException(final BusinessException e, final boolean isDataError) {
		final var errorMessage = isDataError ?
			"ApiExceptionHandler > InvalidDataException > exception: {}, {}"
			: "ApiExceptionHandler > BusinessException > exception: {}, {}";

		log.error(errorMessage, e.getMessage(), e);

		final var errorCode = e.getErrorCode() == null
			? ErrorCode.BAD_REQUEST_ERROR
			: e.getErrorCode();

		return ApiResponseGenerator.fail(errorCode, e.getMessage());
	}

	/**
	 * hibernate validator
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({
			MethodArgumentNotValidException.class,
			IllegalArgumentException.class
	})
	public ApiResponse<List<InvalidArguments>> handleValidation(final MethodArgumentNotValidException e) {
		log.error("ApiExceptionHandler > Invalidation Exception > errorMessage:{}", e.getMessage(), e);
		return handleValidation(e.getBindingResult().getFieldErrors());
	}

	/**
	 * BindException
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({BindException.class})
	public ApiResponse<List<InvalidArguments>> handleValidation(final BindException e) {
		log.error("ApiExceptionHandler > Invalidation Exception > errorMessage:{}", e.getMessage(), e);
		return handleValidation(e.getBindingResult().getFieldErrors());
	}

	private ApiResponse<List<InvalidArguments>> handleValidation(final List<FieldError> fieldErrors) {
		return ApiResponseGenerator.fail(
			ErrorCode.INVALID_PARAMETER,
			fieldErrors
				.stream()
				.map(InvalidArguments::new)
				.collect(Collectors.toList())
		);
	}

	/**
	 * ConstraintViolationException
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({ConstraintViolationException.class})
	public ApiResponse<Void> handleValidation(final ConstraintViolationException e) {
		log.error("ApiExceptionHandler > Invalidation Exception > errorMessage:{}", e.getMessage(), e);
		return ApiResponseGenerator.fail(ErrorCode.INVALID_PARAMETER, e.getMessage());
	}

	/**
	 * spring security AccessDenied
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({AccessDeniedException.class})
	public ApiResponse<Void> handleValidation(final AccessDeniedException e) {
		log.error("ApiExceptionHandler > AccessDenied Exception > errorMessage:{}", e.getMessage(), e);
		return ApiResponseGenerator.fail(ErrorCode.UNAUTHORIZED_ERROR, e.getMessage());
	}

	/**
	 * spring security UnAuthorized
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({UnAuthorizedException.class})
	public ApiResponse<Void> handleValidation(final UnAuthorizedException e) {
		log.error("ApiExceptionHandler > UnAuthorizedException > errorMessage:{}", e.getMessage(), e);
		return ApiResponseGenerator.fail(ErrorCode.UNAUTHORIZED_ERROR, e.getMessage());
	}

	/**
	 * RuntimeException
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({
		HttpRequestMethodNotSupportedException.class,
		MethodArgumentTypeMismatchException.class
	})
	public ApiResponse<Void> handleRuntimeException(final RuntimeException e) {
		log.error("ApiExceptionHandler > RuntimeException > errorMessage:{}", e.getMessage(), e);
		return ApiResponseGenerator.fail(ErrorCode.BAD_REQUEST_ERROR, e.getMessage());
	}

	/**
	 * UNKNOWN_ERROR
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public ApiResponse<Void> handleException(final Exception e) {
		log.error("ApiExceptionHandler > Exception > errorMessage:{}", e.getMessage(), e);
		return ApiResponseGenerator.fail(ErrorCode.UNKNOWN_ERROR);
	}
}
