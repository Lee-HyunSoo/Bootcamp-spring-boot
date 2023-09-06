package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductDAOImpl implements ProductDAO {

    /**
     * @RequiredArgsConstructor 를 통한 생성자 주입
     */
    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager em;

//    @Autowired
//    public ProductDAOImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    /**
     * Spring Data JPA 내의 save() 호출
     * 파라미터로 받아온 product 를 영속성 컨텍스트를 통해 DB에 저장
     * @param product
     * @return savedProduct
     */
    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    /**
     * Spring Data JPA 의 getById() 호출
     * 영속성 컨텍스트 내에 Product 객체를 상속받은 프록시 객체 생성
     * 이 후 해당 객체에 접근하는 시점에 DB에 해당 쿼리가 날아간다.
     * @param number
     * @return selectedProduct
     */
    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getById(number);
        return selectedProduct;
    }

    /**
     * Spring Data JPA 의 findById() 호출
     * 영속성 컨텍스트 내 1차 캐시 조회
     * 조회 후 저장 된 객체가 없다면, flush 를 통해 DB 에서 데이터를 가져와 영속성 컨텍스트에 저장, Optional 형태로 반환
     * @param number
     * @param name
     * @return updatedProduct
     */
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setUpdateAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }
        return updatedProduct;
    }

    /**
     * Spring Data JPA 의 findById() 호출
     * 영속성 컨텍스트 내 1차 캐시 조회
     * 조회 후 저장 된 객체가 없다면, flush 를 통해 DB 에서 데이터를 가져와 영속성 컨텍스트에 저장, Optional 형태로 반환
     * Spring Data JPA 의 delete() 호출
     * @param number
     */
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
