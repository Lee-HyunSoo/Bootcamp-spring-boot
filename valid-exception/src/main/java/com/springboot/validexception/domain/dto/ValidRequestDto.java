package com.springboot.validexception.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidRequestDto {

    /* null, "", " " 을 허용하지 않는다. */
    @NotBlank
    String name;

    /* 이메일 형식을 검사한다. "" 는 허용한다. */
    @Email
    String email;

    /* 정규식을 검사한다. */
    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    String phoneNumber;

    /* min 이상의 값을 허용한다. max 이하의 값을 허용한다. */
    @Min(value = 20)
    @Max(value = 40)
    int age;

    /* min 이상 max 이하의 범위를 허용한다. */
    @Size(min = 0, max = 40)
    String description;

    /* 양수를 허용한다. */
    @Positive
    int count;

    /* true 인지 체크한다. null 값은 체크하지 않는다. */
    @AssertTrue
    boolean booleanCheck;
}
