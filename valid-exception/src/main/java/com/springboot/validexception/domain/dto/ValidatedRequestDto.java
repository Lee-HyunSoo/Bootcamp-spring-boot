package com.springboot.validexception.domain.dto;

import com.springboot.validexception.config.annotation.Telephone;
import com.springboot.validexception.domain.group.ValidationGroup1;
import com.springboot.validexception.domain.group.ValidationGroup2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedRequestDto {

    /**
     * @Null : null 만 허용
     * @NotNull : null 을 허용하지 않는다. "", " " 는 허용한다.
     * @NotEmpty : null, "" 를 허용하지 않는다. " " 는 허용한다.
     * @NotBlank : null, "", " " 을 허용하지 않는다.
     */
    @NotBlank
    private String name;

    /**
     * @Email : 이메일 형식을 검사
     * "" 는 허용한다.
     */
    @Email
    private String email;

    /**
     * @Pattern(regexp = "$expression") : 정규식을 검사한다.
     * @Telephone : custom annotation
     */
//    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Telephone
    private String phoneNumber;

    /**
     * 최대값, 최소값 검증
     * @Min : min 이상의 값을 허용한다.
     * @Max : max 이하의 값을 허용한다.
     * 별다른 기능이 없는 마커 인터페이스 사용, 오직 그룹화 목적으로 사용
     * Controller 에서 @Validated 사용 시, 해당 인터페이스를 함께 등록해야 유효성 검사를 수행한다.
     */
    @Min(value = 20, groups = ValidationGroup1.class)
    @Max(value = 40, groups = ValidationGroup1.class)
    private int age;

    /**
     * @Size : 문자열 길이 검증
     * min 이상 max 이하의 범위를 허용한다.
     */
    @Size(min = 0, max = 40)
    private String description;

    /**
     * @Positive : 양수를 허용
     * @Negative : 음수를 허용
     * Controller 에서 @Validated 사용 시, 해당 인터페이스를 함께 등록해야 유효성 검사를 수행한다.
     */
    @Positive(groups = ValidationGroup2.class)
    private int count;

    /**
     * @AssertTrue : true 인지 체크한다. null 값은 체크하지 않는다.
     * @AssertFalse : false 인지 체크한다. null 값은 체크하지 않는다.
     */
    @AssertTrue
    private boolean booleanCheck;

    /**
     * 자릿 수 범위 검증
     * @Digits(integer = $number1, fraction = $number2)
     * $number1 의 정수 자릿수와 $number2 의 소수 자릿수를 허용
     */
}
