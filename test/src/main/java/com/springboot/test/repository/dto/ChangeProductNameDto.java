package com.springboot.test.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeProductNameDto {

    private Long number;
    private String name;
}
