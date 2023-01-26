package kr.co.mz.support.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.mz.support.code.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "API 응답 정보")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {

	@Schema(description = "응답 코드")
	private String code;

	@Schema(description = "응답 메시지")
	private String message;

	@Schema(description = "응답 데이터")
	private T data;

	ApiResponse(ErrorCode errorCode) {
		this(errorCode, null, null);
	}

	ApiResponse(ErrorCode errorCode, T data) {
		this.code = errorCode.getCode();
		this.message = errorCode.getDefaultMessage();
		this.data = data;
	}

	ApiResponse(ErrorCode errorCode, String responseMessage, T data) {
		this.code = errorCode.getCode();
		this.message = (responseMessage == null ? errorCode.getDefaultMessage() : responseMessage);
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
