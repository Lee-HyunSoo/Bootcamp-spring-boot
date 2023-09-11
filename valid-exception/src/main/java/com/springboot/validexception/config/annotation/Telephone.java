package com.springboot.validexception.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target : 어노테이션을 어디서 선언할 수 있는지 정의
 * PACKAGE, TYPE, CONSTRUCTOR, METHOD, PARAMETER ...
 */

/**
 * @Retention : 어노테이션이 실제로 적용되고 유지되는 범위를 의미
 * RUNTIME : 컴파일 이후에도 JVM 에 의해 계속 참조
 * CLASS : 컴파일러가 클래스를 참조할 때 까지 유지
 * SOURCE : 컴파일러 전까지만 유지. 컴파일 이후에는 사라진다.
 */

/**
 * @Constraint : TelephoneValidator 와 매핑
 * message() : 유효성 검사가 실패할 경우 반환되는 메세지
 * groups() : 유효성 검사가 사용하는 그룹으로 설정
 * payload() : 사용자가 추가 정보를 위해 전달하는 값
 *
 * 현재 groups() 와 payload() 는 정의하지 않았다.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {

    String message() default "전화번호 형식이 일치하지 않습니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}
