package com.springboot.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String userId;
    private String password;
    private String email;
    private String userName;
    private LocalDate dateOfBirth;

    private Address address;

    private String[] hobbyArray;
    private boolean foreigner;
    private String gender;
    private List<String> hobbyList;

    private List<Card> cards;

}
