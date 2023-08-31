package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    /**
     * URL : http://localhost:8090/api/v1/put-api/member
     * PUT 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 Map 형태로 저장
     * @param putData
     * @return sb.toString()
     */
    @PutMapping("/member")
    public String postMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet()
                .forEach(m -> sb.append(m.getKey() + " " + m.getValue()).append("\n"));
        return sb.toString();
    }

    /**
     * URL : http://localhost:8090/api/v1/put-api/member1
     * PUT 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 MemberDto 형태로 저장
     * @param memberDto
     * @return memberDto.toString()
     */
    @PutMapping("/member1")
    public String postMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    /**
     * URL : http://localhost:8090/api/v1/put-api/member2
     * PUT 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 MemberDto 형태로 저장
     * @param memberDto
     * @return memberDto
     */
    @PutMapping("/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    /**
     * URL : http://localhost:8090/api/v1/put-api/member3
     * PUT 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 MemberDto 형태로 저장
     * @param memberDto
     * @return ResponseEntity
     */
    @PutMapping("/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberDto);
    }

}
