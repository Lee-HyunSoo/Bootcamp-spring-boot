package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    /**
     * URL : http://localhost:8090/api/v1/get-api/hello
     * GET 요청 -> 문자열 response
     * @return Hello World
     */
    @GetMapping("/hello")
    public String getHello() {
        log.info("getHello Method 호출");
        return "Hello World";
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/name
     * GET 요청 -> 문자열 response
     * @return Flature
     */
    @GetMapping("/name")
    public String getName() {
        log.info("getName Method 호출");
        return "Flature";
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/variable1/1
     * GET 요청 -> @PathVariable 을 통해 경로변수 캐치
     * @param variable
     * @return variable
     */
    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        log.info("@PathVariable : {}", variable);
        return variable;
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/variable2/2
     * GET 요청 -> @PathVariable("variable")을 통해 경로변수 캐치, 캐치할 변수명 설정 가능
     * @param var
     * @return var
     */
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/request1?name=param1&email=param2&organization=param3
     * GET 요청 -> @RequestParam 을 통해 쿼리 파라미터 저장
     * @param name
     * @param email
     * @param organization
     * @return name + " " + email + " " + organization
     */
    @GetMapping("/request1")
    public String getRequestParam1(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/request2?key1=value1&key2=value2
     * GET 요청 -> @RequestParam 을 통해 쿼리 파라미터를 Map 형태로 저장
     * @param param
     * @return sb.toString()
     */
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet()
                .forEach(m -> sb.append(m.getKey() + " : " + m.getValue()).append("\n"));
        return sb.toString();
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/request3?name=value1&email=value2&organization=value3
     * GET 요청 -> @RequestParam 생략, 쿼리 파라미터를 MemberDto 형태로 저장
     * @param memberDto
     * @return memberDto.toString()
     */
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }

    /**
     * URL : http://localhost:8090/api/v1/get-api/request4?name=param1&email=param2&organization=param3
     * GET 요청 -> @RequestParam 을 통해 쿼리 파라미터 저장
     * @ApiOperation : 해당 API 에 대한 설명 작성
     * @ApiParam : 파라미터에 대한 설명 및 설정
     * @param name
     * @param email
     * @param organization
     * @return name + " " + email + " " + organization
     */
    @ApiOperation(value = "GET 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping("/request4")
    public String getRequestParam4(@ApiParam(value = "이름", required = true) @RequestParam String name,
                                   @ApiParam(value = "이메일", required = true) @RequestParam String email,
                                   @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }
}
