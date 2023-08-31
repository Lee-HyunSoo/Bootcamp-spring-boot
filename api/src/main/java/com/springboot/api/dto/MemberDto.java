package com.springboot.api.dto;

import lombok.Data;

@Data
public class MemberDto {

    /*
    {
      "name" : "Flature",
      "email" : "test@gmail.com",
      "organization" : "Around Hub Studio"
    }
    */
    private String name;
    private String email;
    private String organization;

}
