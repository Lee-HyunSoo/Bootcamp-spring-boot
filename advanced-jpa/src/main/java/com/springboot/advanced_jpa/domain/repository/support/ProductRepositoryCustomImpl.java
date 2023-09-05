package com.springboot.advanced_jpa.domain.repository.support;

import com.springboot.advanced_jpa.domain.entity.Product;
import com.springboot.advanced_jpa.domain.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * Custom 쿼리 인터페이스의 구현체
 * QuerydslRepositorySupport : 구현체 내에서 QueryDsl 사용을 서포팅 해주는 추상클래스
 * 단점으로는, JPAQueryFactory 를 지원하지 않아 SELECT 로 시작할 수 없다.
 */
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {


    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        QProduct qProduct = QProduct.product;

        return from(qProduct)
                .where(qProduct.name.eq(name))
                .select(qProduct)
                .fetch();
    }
}
