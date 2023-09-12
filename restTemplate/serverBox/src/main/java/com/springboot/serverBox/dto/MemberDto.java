package com.springboot.serverBox.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberDto {

    private String name;
    private String email;
    private String organization;

}
