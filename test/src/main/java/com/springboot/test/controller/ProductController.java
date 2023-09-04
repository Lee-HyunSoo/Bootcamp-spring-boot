package com.springboot.test.controller;


import com.springboot.test.repository.dto.ChangeProductNameDto;
import com.springboot.test.repository.dto.ProductRequestDto;
import com.springboot.test.repository.dto.ProductResponseDto;
import com.springboot.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * @RequiredArgsConstructor 를 통한 생성자 주입
     */
    private final ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    /**
     * ProductService 내의 getProduct() 호출, ProductResponseDto 객체를 return 받음
     * Message Body 에 ProductResponseDto 객체를 담아 response
     * @param number
     * @return ResponseEntity.status(HttpStatus.OK).body(productResponseDto)
     */
    @GetMapping
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    /**
     * ProductService 내의 saveProduct() 호출, ProductResponseDto 객체를 return 받음
     * Message Body 에 ProductResponseDto 객체를 담아 response
     * @param productRequestDTO
     * @return ResponseEntity.status(HttpStatus.OK).body(productResponseDto)
     */
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDTO) {
        ProductResponseDto productResponseDto = productService.saveProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    /**
     * ProductService 내의 changeProductName() 호출, ProductResponseDto 객체를 return 받음
     * Message Body 에 ProductResponseDto 객체를 담아 response
     * @param changeProductNameDto
     * @return ResponseEntity.status(HttpStatus.OK).body(productResponseDto)
     */
    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName
            (@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductNameDto.getNumber(), changeProductNameDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    /**
     * ProductService 내의 deleteProduct() 호출, DELETE
     * Message Body 에 String 을 담아 response
     * @param number
     * @return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.")
     */
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
