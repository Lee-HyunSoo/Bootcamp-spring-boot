package com.springboot.validexception.common;

/**
 * 열거형(enum) 클래스를 별도로 만들어도 되지만, 확장성을 위해 
 * Constants 라는 상수들을 통합 관리하는 클래스 내부에 enum 클래스 생성
 */
public class Constants {

    /**
     * 커스텀 예외 클래스에서 어떤 도메인에서 문제가 발생했는지 보여주는데 사용
     */
    public enum ExceptionClass {

        PRODUCT("Product");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }
    }

}
