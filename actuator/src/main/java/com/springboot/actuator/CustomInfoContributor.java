package com.springboot.actuator;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.actuate.info.Info.*;

/**
 * 엑추에이터 커스터마이징
 * /info 엔드포인트의 내용을 추가한 것 처럼 application.properties 파일 내에 내용을 추가
 * -> 많은 내용을 담을 때는 관리 측면에서 좋지 않다.
 * 
 * 때문에 커스텀 기능을 설정 할 때는 별도의 구현체 클래스를 작성해서 내용을 추가한다.
 * -> InfoContributor 인터페이스를 상속받아 구현체를 만들면 된다.
 */

@Component
public class CustomInfoContributor implements InfoContributor {

    /**
     * Builder builder : Actuator 패키지의 Info 클래스 안에 정의 되어있는 클래스
     * Info 엔드포인트에서 보여줄 내용을 담는 역할
     */
    @Override
    public void contribute(Builder builder) {
        Map<String, Object> content = new HashMap<>();
        content.put("code-info", "InfoContributor 구현체에서 정의한 정보입니다.");
        builder.withDetail("custom-info-contributor", content);

    }
}
