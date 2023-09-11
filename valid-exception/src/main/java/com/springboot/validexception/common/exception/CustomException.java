package com.springboot.validexception.common.exception;

import com.springboot.validexception.common.Constants;
import org.springframework.http.HttpStatus;

/**
 * @ControllerAdvice 와 @ExceptionHandler 의 무분별한 예외 처리를 방지하기 위한 커스텀 예외 클래스
 * 1. 에러 타입 : HttpStatus.reasonPhrase()
 * 2. 에러 코드 : HttpStatus.value()
 * 3. 메세지 : 상황별 상세 메세지
 *
 * 클래스 구조
 * 1. 커스텀 예외는 예외가 발생하는 상황에 해당하는 상위 클래스를 상속받는다.
 * 2. CustomException - Exception - Throwable
 * 3. Exception 클래스는 Throwable 클래스의 생성자 호출, message 변수 값을 detailMessage 로 전달 받는다. (getter)
 * 4. HttpStatus 를 커스텀 예외 클래스에 포함
 * 5. getHttpStatusCode() : HttpStatus 의 value (1xx, 2xx, 3xx, 4xx, 5xx)
 * 6. getHttpStatusType() : HttpStatus 의 reasonPhrase (OK, Found, Not Found ... )
 * 7. getHttpStatus() : HttpStatus Getter
 */
public class CustomException extends Exception {

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
