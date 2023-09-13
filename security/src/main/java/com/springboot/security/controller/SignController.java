package com.springboot.security.controller;

import com.springboot.security.domain.dto.SignInResultDto;
import com.springboot.security.domain.dto.SignUpResultDto;
import com.springboot.security.service.SignService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-in")
    public SignInResultDto signIn(
            @ApiParam(value = "ID", required = true) @RequestParam String id,
            @ApiParam(value = "Password", required = true) @RequestParam String password
    ) throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}", id);
        SignInResultDto signInResultDto = signService.signIn(id, password);

        if (signInResultDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. id : {}, token : {}", id, signInResultDto.getToken());
        }
        return signInResultDto;
    }

    @PostMapping("/sign-up")
    public SignUpResultDto singUp(
            @ApiParam(value = "ID", required = true) @RequestParam String id,
            @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "권한", required = true) @RequestParam String role
    ) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}",
                id, name, role);
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);
        log.info("[signUp] 회원가입을 완료했습니다. id : {}", id);

        return signUpResultDto;
    }

    @GetMapping("/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지 되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> messageBody = getMessageBody(httpStatus);

        return new ResponseEntity<>(messageBody, httpHeaders, httpStatus);
    }

    private Map<String, String> getMessageBody(HttpStatus httpStatus) {
        Map<String, String> body = new HashMap<>();
        body.put("error type", httpStatus.getReasonPhrase());
        body.put("code", "400");
        body.put("message", "에러 발생");
        return body;
    }
}
