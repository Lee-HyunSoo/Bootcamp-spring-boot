package com.springboot.serverBox.controller;

import com.springboot.serverBox.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crud-api")
public class CrudController {

    @GetMapping
    public String getName() {
        return "Flature";
    }

    @GetMapping("/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("/param")
    public String getNameWithParam(@RequestParam String name) {
        return "Hello. " + name + "!";
    }

    @PostMapping
    public ResponseEntity<MemberDto> getMember(
            @RequestBody MemberDto request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ) {
        System.out.println("memberDto.getName() = " + request.getName());
        System.out.println("memberDto.getEmail() = " + request.getEmail());
        System.out.println("memberDto.getOrganization() = " + request.getOrganization());

        MemberDto memberDto = new MemberDto();
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setOrganization(organization);

        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    /**
     * 임의의 HTTP 헤더를 받는 컨트롤러
     */
    @PostMapping("/add-header")
    public ResponseEntity<MemberDto> addHeader(
            @RequestHeader(value = "my-header") String header,
            @RequestBody MemberDto memberDto) {
        System.out.println("header = " + header);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

}
