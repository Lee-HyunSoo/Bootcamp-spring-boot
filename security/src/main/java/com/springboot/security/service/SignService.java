package com.springboot.security.service;

import com.springboot.security.domain.dto.SignInResultDto;
import com.springboot.security.domain.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
