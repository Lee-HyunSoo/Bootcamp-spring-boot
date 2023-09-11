package com.springboot.validexception.controller;

import com.springboot.validexception.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.validexception.common.Constants.*;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping
    public void getRuntimeException() {
        throw new RuntimeException("getRuntimeException Controller 호출");
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException {
        throw new CustomException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "getCustomException Method 호출");
    }

    /**
     * 컨트롤러 클래스 내에 @ExceptionHandler 를 사용하면 해당 클래스에 국한해 예외 처리 가능
     * 글로벌 설정보다 우선순위를 가진다.
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerException(RuntimeException e, HttpServletRequest request) {
        /* 클라이언트에게 보낼 오류 응답메세지 설정 */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("클래스 내 handlerException 호출 : {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, httpStatus);
    }
}
