package com.springboot.rest.service;

import com.springboot.rest.dto.MemberDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    /**
     * UriComponentsBuilder
     * Spring Framework 에서 제공하는 클래스, 여러 파라미터를 연결해 URI 형식으로 만든다.
     *
     * 1. fromUriString() : URL 입력
     * 2. path() : 세부 경로 입력
     * 3. encode() : 인코딩 문자 셋, 기본 값은 UTF-8
     * 4. build() : 빌더 생성 종료 -> UriComponents 타입 return
     * 5. toUri() : UriComponents -> URI 타입으로 변경
     * 만약 String 타입 URI 라면, toUriString() 을 사용하면 된다.
     * 6. expand() : @PathVariable 로 받을 변수 입력, path() 에 경로변수가 있어야한다.
     * 7. queryParam() : @RequestParam 으로 받을 변수 입력 (key, value) 형식
     * 
     * RestTemplate
     * 다른 서버로 요청을 보내고 응답을 받을 수 있게 도와주는 템플릿
     *
     * 1. getForEntity(uri, 응답 타입)
     * 2. postForEntity(uri, dto 객체, dto 타입)
     * 3. exchange(requestEntity, requestEntity body 에 담긴 데이터 타입)
     * exchange 의 경우 모든 형식의 HTTP 요청을 생성할 수 있다. 때문에 대부분 exchange 를 사용한다.
     */
    /* GET 요청 RestTemplate */
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    public String getNameWithPathVariable() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/{name}")
                .encode()
                .build()
                .expand("Flature") // 복수의 값을 넣어야할 경우 , 를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/param")
                .queryParam("name", "Flature")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    /* POST 요청 RestTemplate */
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api")
                .queryParam("name", "Flature")
                .queryParam("email", "flature@wikibooks.co.kr")
                .queryParam("organization", "wikibooks")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature!!");
        memberDto.setEmail("flature@gmail.com");
        memberDto.setOrganization("Around Hub Studio");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);
        return responseEntity;
    }

    /**
     * RequestEntity
     * 대부분 외부 API 는 토큰키를 받아 서비스 접근을 인증하는 방식으로 작동한다.
     * 이때 토큰값을 헤더에 담아 전달하는 방식이 가장 많이 사용된다.
     * 때문에 RequestEntity 를 사용하여 헤더를 설정한다.
     *
     * 1. post() : URI 설정
     * 2. header() : 헤더의 키와 값을 설정
     * 3. body() : 데이터를 담는다.
     */
    public ResponseEntity<MemberDto> postWithHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/add-header")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("flature");
        memberDto.setEmail("flature@wikibooks.co.kr");
        memberDto.setOrganization("Around Hub Studio");

        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "wikibooks API")
                .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);
        return responseEntity;
    }

    /**
     * RestTemplate 는 HTTPClient 를 추상화 하고 있다. 둘의 가장 큰 차이는 커넥션 풀이다.
     * RestTemplate 는 커넥션 풀을 지원하지 않는다. 때문에 매번 호출 시 포트를 열어 커넥션을 생성하는데,
     * TIME_WAIT 상태가 된 소켓을 다시 사용하려고 접근하면 재사용 할 수가 없다.
     * 때문에 커넥션 풀 기능을 활성화해 재사용할 수 있게 해야한다.
     * 이 기능을 활성화하는 가장 대표적인 방법이 Apache 의 HttpClient 로 대체해 사용하는 방식이다.
     *
     * RestTemplate 의 경우 ClientHttpRequestFactory 를 파라미터로 받는 생성자가 존재한다.
     * HttpComponentsClientHttpRequestFactory 객체를 생성해 ClientHttpRequestFactory 를 사용하면,
     * RestTemplate 의 Timeout 설정을 할 수 있다.
     *
     * HttpClient, CloseableHttpClient 를 통해 커넥션 풀을 설정할 수 있다.
     */
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        HttpClient client = HttpClientBuilder.create()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build();

        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
}
