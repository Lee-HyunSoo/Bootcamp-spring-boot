package com.springboot.test.controller;

import com.google.gson.Gson;
import com.springboot.test.repository.dto.ProductRequestDto;
import com.springboot.test.repository.dto.ProductResponseDto;
import com.springboot.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    /**
     * MockMvc
     * 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공 / api 테스트도 가능
     *
     * 1. given()
     * 주입한 Mock 객체를 통해 테스트할 메서드 호출
     *
     * 2. willReturn()
     * given() 을 통해 실행한 결과 값과 일치해야한다. 즉, DB 에 접근하지 않고 테스트하기 위한 값을 세팅한다.
     *
     * 3. perform()
     * 요청을 전송하는 역할
     * andExpect() : perform 의 결과로 return 받은 ResultActions 객체의 값을 검증
     * andDo(print()) : 요청 / 응답 전체 메세지를 확인
     *
     * 4. GSON
     * JSON Object -> JAVA Object 또는 JAVA Object -> JSON Object 를 도와준다.
     *
     * 5. MediaType.APPLICATION_JSON
     * new MediaType("application", "json") 을 요청 시 추가
     *
     * 6. verify()
     * 해당 메서드가 실행되었는지 검증
     */
    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {
        // given
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 5000, 2000)
        );

        String productId = "123";

        // when
        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        // then
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        // given
        given(productService.saveProduct(new ProductRequestDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductRequestDto productRequestDto = ProductRequestDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productRequestDto);

        // when
        mockMvc.perform(post("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        // then
        verify(productService).saveProduct(new ProductRequestDto("pen", 5000, 2000));
    }

}