package com.springboot.validexception.controller;

import com.springboot.validexception.domain.dto.ValidRequestDto;
import com.springboot.validexception.domain.dto.ValidatedRequestDto;
import com.springboot.validexception.domain.group.ValidationGroup1;
import com.springboot.validexception.domain.group.ValidationGroup2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidationController {

    /**
     * @Valid : 자바에서 지원하는 어노테이션
     * 유효성 검사를 수행한다.
     */
    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(
            @Valid
            @RequestBody ValidRequestDto validRequestDto) {
        log.info("validRequestDto : {}", validRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
    }

    /**
     * @Validated : 스프링에서 제공하는 어노테이션
     * 특정 그룹을 지정하지 않는 경우, groups 속성을 설정하지 않은 필드에 대해서만 유효성 검사를 수행한다.
     * 특정 그룹을 지정한 경우, 지정한 그룹으로 설정 된 필드에 대해서만 유효성 검사를 수행한다.
     */
    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation(
            @Validated
            @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info("validatedRequestDto : {}", validatedRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1(
            @Validated(ValidationGroup1.class)
            @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info("validatedRequestDto : {}", validatedRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2(
            @Validated(ValidationGroup2.class)
            @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info("validatedRequestDto : {}", validatedRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("/validated/all-group")
    public ResponseEntity<String> checkValidation3(
            @Validated({ ValidationGroup1.class, ValidationGroup2.class })
            @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info("validatedRequestDto : {}", validatedRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

}
