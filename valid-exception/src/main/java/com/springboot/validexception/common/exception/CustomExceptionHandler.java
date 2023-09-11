package com.springboot.validexception.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @RestControllerAdvice
 * @RestController 에서 발생하는 예외를 한 곳에서 관리하고 처리할 수 있게 하는 기능
 * basePackages 옵션을 통해 예외를 처리하는 범위를 지정할 수 있다.
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.springboot.validexception")
public class CustomExceptionHandler {

    /**
     * @ExceptionHandler
     * @Controller 나 @RestController 가 적용 된 빈에서 발생하는 예외를 처리하는 메서드를 만들 때 사용
     * value : 어떤 에러가 발생했을 때 해당 메서드를 동작시킬지 설정
     * 현재 예제에서는 RuntimeException 이 발생했을 경우 해당 메서드가 동작한다.
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerException(RuntimeException e, HttpServletRequest request) {
        /* 클라이언트에게 보낼 오류 응답메세지 설정 */
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Advice 내 handlerException 호출 : {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, httpStatus);
    }

    /**
     * 커스텀 예외 처리 메서드
     * 파라미터를 RuntimeException 에서 CustomException 으로 변경
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> handlerException(CustomException e, HttpServletRequest request) {
        /* 클라이언트에게 보낼 오류 응답메세지 설정 */
        HttpHeaders httpHeaders = new HttpHeaders();

        log.error("Advice 내 handlerException 호출 : {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, e.getHttpStatus());
    }

}
