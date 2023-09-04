package com.springboot.test.service.impl;

import com.springboot.test.data.dto.ProductRequestDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * @RequiredArgsConstructor 를 통한 생성자 주입
     */
//    private final ProductDAO productDAO;
    private final ProductRepository productRepository;

//    @Autowired
//    public ProductServiceImpl(ProductDAO productDAO) {
//        this.productDAO = productDAO;
//    }

//    @Autowired
//    public ProductServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    /**
     * 파라미터로 넘어온 number 를 log 를 통해 출력
     * Spring Data JPA 내 findById() 의 return 값인 product 를 log 를 통해 출력
     * @param number
     * @return setProductResponseDto(product)
     */
    @Override
    public ProductResponseDto getProduct(Long number) {
        log.info("[getProduct] input number : {}", number);

        Product product = productRepository.findById(number).get();

        log.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());

        return setProductResponseDto(product);
    }

    /**
     * 파라미터로 넘어온 productRequestDto 를 log 를 통해 출력
     * Spring Data JPA 내 save() 의 return 값인 savedProduct 를 log 를 통해 출력
     * @param productRequestDto
     * @return setProductResponseDto(savedProduct)
     */
    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        log.info("[saveProduct] productRequestDto : {}", productRequestDto.toString());

        Product product = setProduct(productRequestDto);
        Product savedProduct = productRepository.save(product);

        log.info("[saveProduct] savedProduct : {}", savedProduct);

        return setProductResponseDto(savedProduct);
    }

    /**
     * 파라미터로 넘어온 number, name 을 log 를 통해 출력
     * Spring Data JPA 내 findById() 의 return 값인 foundProduct 를 log 를 통해 출력
     * Spring Data JPA 내 save() 의 return 값인 changedProduct 를 log 를 통해 출력
     * @param number
     * @param name
     * @return setProductResponseDto(changedProduct)
     */
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        log.info("[changeProductName] product number : {}, name : {}", number, name);

        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);

        log.info("[changeProductName] foundProduct : {}", foundProduct);

        Product changedProduct = productRepository.save(foundProduct);

        log.info("[changeProductName] changedProduct : {}", changedProduct);

        return setProductResponseDto(changedProduct);
    }

    /**
     * Spring Data JPA 내 deleteById() 실행
     * @param number
     */
    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }

    /**
     * Product 객체를 통해 ProductResponseDto 객체 set, return
     * @param product
     * @return productResponseDto
     */
    private ProductResponseDto setProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());
        return productResponseDto;
    }

    /**
     * ProductRequestDto 객체를 통해 Product 객체 set, return
     * @param productRequestDTO
     * @return product
     */
    private Product setProduct(ProductRequestDto productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        return product;
    }
}
