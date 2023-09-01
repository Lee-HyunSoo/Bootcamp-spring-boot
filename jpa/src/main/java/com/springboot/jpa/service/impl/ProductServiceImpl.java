package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductRequestDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * @RequiredArgsConstructor 를 통한 생성자 주입
     */
    private final ProductDAO productDAO;

//    @Autowired
//    public ProductServiceImpl(ProductDAO productDAO) {
//        this.productDAO = productDAO;
//    }

    /**
     * ProductDAO 내의 selectProduct() 호출, SELECT 된 Product 객체를 return 받음
     * setProductResponseDto() 호출, ProductResponseDto 객체 생성 및 set
     * @param number
     * @return setProductResponseDto(product)
     */
    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);
        return setProductResponseDto(product);
    }

    /**
     * setProduct() 호출, Product 객체 생성 및 set
     * ProductDAO 내의 insertProduct() 호출, INSERT 된 Product 객체를 return 받음
     * setProductResponseDto() 호출, ProductResponseDto 객체 생성 및 set
     * @param productRequestDTO
     * @return setProductResponseDto(savedProduct)
     */
    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDTO) {
        Product product = setProduct(productRequestDTO);
        Product savedProduct = productDAO.insertProduct(product);
        return setProductResponseDto(savedProduct);
    }

    /**
     * ProductDAO 내의 updateProductName() 호출, UPDATE 된 Product 객체를 return 받음
     * setProductResponseDto() 호출, ProductResponseDto 객체 생성 및 set
     * @param number
     * @param name
     * @return setProductResponseDto(changedProduct)
     */
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);
        return setProductResponseDto(changedProduct);
    }

    /**
     * ProductDAO 내의 deleteProduct() 호출, Product DELETE
     * @param number
     */
    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
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
