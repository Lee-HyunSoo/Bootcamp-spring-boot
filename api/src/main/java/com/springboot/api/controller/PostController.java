package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    /**
     * URL : http://localhost:8090/api/v1/post-api/domain
     * POST 요청 -> 문자열 response
     * @return Hello Post API
     */
    @PostMapping("/domain")
    public String postExample() {
        return "Hello Post API";
    }

    /**
     * URL : http://localhost:8090/api/v1/post-api/member
     * POST 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 Map 형태로 저장
     * @param postData
     * @return sb.toString()
     */
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet()
                .forEach(m -> sb.append(m.getKey() + " : " + m.getValue()).append("\n"));
        return sb.toString();
    }

    /**
     * URL : http://localhost:8090/api/v1/post-api/member2
     * POST 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 MemberDto 형태로 저장
     * @param memberDto
     * @return memberDto.toString()
     */
    @PostMapping("/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

}
