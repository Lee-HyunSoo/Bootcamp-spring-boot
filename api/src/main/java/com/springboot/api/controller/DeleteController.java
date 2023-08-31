package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    /**
     * URL : http://localhost:8090/api/v1/delete-api/1
     * DELETE 요청 -> @PathVariable 을 통해 경로변수 캐치
     * DELETE 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 Map 형태로 저장
     * @param variable
     * @return variable
     */
    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    /**
     * URL : http://localhost:8090/api/v1/delete-api/request1?email=value
     * DELETE 요청 -> @RequestParam 을 통해 쿼리 파라미터 저장
     * DELETE 요청 -> 전송 된 json 데이터를 @RequestBody을 통해 Map 형태로 저장
     * @param email
     * @return "e-mail : " + email
     */
    @DeleteMapping("/request1")
    public String getRequestParam(@RequestParam String email) {
        return "e-mail : " + email;
    }

}
