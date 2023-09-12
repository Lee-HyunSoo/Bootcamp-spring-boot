package com.springboot.rest.service;

import com.springboot.rest.dto.MemberDto;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 실무의 어플리케이션들은 최신 스프링 부트 버전보다 낮은 경우가 많다.
 * 때문에 RestTemplate 를 많이 사용하고 있지만, 최신 버전에선 RestTemplate 가 지원 중단되어 WebClient 를 사용하길 권장한다.
 *
 * Spring WebFlux 는 HTTP 요청을 수행하는 클라이언트로 WebClient 를 제공한다.
 * WebClient 는 Reactor 기반으로 동작하는 API 로, 스레드와 동시성 문제를 벗어나 비동기 형식으로 사용 가능하다.
 * 리액티브 프로그래밍을 사용하면 여러 동시적 기능을 사용할 수 있다.
 *
 * WebClient 생성 방법
 * 1. builder()
 * 2. create()
 */
@Service
public class WebClientService {

    /**
     * builder()
     * 1. baseUrl() : 기본 URL 설정
     * 2. defaultHeader() : 헤더의 값 설정
     * 3. defaultCookie() : 쿠키 설정
     * 4. defaultUriVariable() : 기본 URI 확장 값 설정
     * 5. filter() : 요청에 대한 필터 설정
     * 일반적으로 WebClient 객체를 이용 시 이렇게 생성 후 재사용하는 방식으로 구현하는게 제일 좋다.
     * 
     * WebClient
     * 1. get(), post(), put(), delete() 설정 가능
     * 2. uri() : URI 확장 가능
     * 3. retrieve() : 요청에 대한 응답을 받았을 때 값을 추출
     * 4. bodyToMono() : retrieve() 로 추출한 값을 리턴 타입을 설정해 문자열 객체를 받아온다.
     * 5. block() : 블로킹 형식으로 동작, WebClient 는 기본 값이 논블로킹 방식이라 바꿔야 한다.
     */
    /* GET 요청 WebClient */
    public String getName() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .get()
                .uri("/api/v1/crud-api")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * 1. toEntity() : bodyToMono() 대신 사용. ResponseEntity 타입으로 응답을 받을 수 있다.
     */
    public String getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9090");

        ResponseEntity<String> responseEntity = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/crud-api/{name}")
                        .build("getNameWithPathVariable"))
                .retrieve()
                .toEntity(String.class)
                .block();

        return responseEntity.getBody();
    }

    /**
     * exchangeToMono() : 응답 결과 코드에 따라 다르게 응답을 설정 가능
     * clientResponse 의 결과 상태에 따라 if 문 분기를 만들어 활용
     */
    public String getNameWithParameter() {
        WebClient webClient = WebClient.create("http://localhost:9090");

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
                        .queryParam("name", "getNameWithParameter")
                        .build())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    /* POST 요청 WebClient */
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature!!");
        memberDto.setEmail("flature@gmail.com");
        memberDto.setOrganization("Around Hub Studio");

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/crud-api")
                        .queryParam("name", "post")
                        .queryParam("email", "paramAndBody")
                        .queryParam("organization", "webClient")
                        .build()
                )
                .bodyValue(memberDto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature!!");
        memberDto.setEmail("flature@gmail.com");
        memberDto.setOrganization("Around Hub Studio");

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/crud-api/add-header")
                        .build()
                )
                .bodyValue(memberDto)
                .header("my-header", "postWithHeader")
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

}
