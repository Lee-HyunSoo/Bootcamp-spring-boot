package com.springboot.jpa.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {

    private String name;
    private int price;
    private int stock;

}
