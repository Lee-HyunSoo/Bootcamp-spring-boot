package com.springboot.jpa.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductRequestDto {

    private String name;
    private int price;
    private int stock;

}
