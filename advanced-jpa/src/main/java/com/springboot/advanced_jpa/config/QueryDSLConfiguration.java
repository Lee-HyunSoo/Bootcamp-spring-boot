package com.springboot.advanced_jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * QueryDSL 을 사용하기 위해 JPAQueryFactory 에 EntityManager 를 할당하는 부분을 컨테이너에 등록
 * 이 후 다른 곳에서 @Autowired 로 가져다 사용할 수 있다.
 */
@Configuration
public class QueryDSLConfiguration {

    @PersistenceContext
    EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
