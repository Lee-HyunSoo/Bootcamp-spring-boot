package com.springboot.advanced_jpa.domain.repository;

import com.springboot.advanced_jpa.domain.entity.Product;
import com.springboot.advanced_jpa.domain.repository.support.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    /**
     * find...By
     * SELECT 를 통해 조회
     */
    Optional<Product> findByNumber(Long number);

    List<Product> findAllByName(String name);

    Product queryByNumber(Long number);

    /**
     * exists...By
     * 특정 데이터가 존재하는지 확인
     */
    boolean existsByNumber(Long number);

    /**
     * count...By
     * SELECT 를 통해 조회 된 결과로 나온 레코드의 개수 return
     */
    int countByName(String name);

    // delete...By, remove...By

    /**
     * delete...By, remove...By
     * 삭제 쿼리
     * remove 의 경우 삭제한 개수를 return
     */
    void deleteByNumber(Long number);
    long removeByName(String name);

    // ...Fist<number>..., ...Top<number>...

    /**
     * ...Fist<number>..., ...Top<number>... (동일한 기능)
     * SELECT 를 통해 조회 된 결과의 개수를 제한해서 가져오는 것
     */
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    /**
     * Is, Equals (동일한 기능)
     * 값의 일치를 조건으로 사용
     * findByNumber() 와 동일하게 동작한다.
     */
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    /**
     * Not, IsNot (동일한 기능)
     * 값의 불일치를 조건으로 사용
     */
    Product findByNumberNot(Long number);
    Product findByNumberIsNot(Long number);

    /**
     * Null, (Is)NotNull
     * 값이 null 인지 검사
     */
    List<Product> findByLastModifiedDateNull();
    List<Product> findByLastModifiedDateIsNull();
    List<Product> findByLastModifiedDateNotNull();
    List<Product> findByLastModifiedDateIsNotNull();

    /**
     * boolean 칼럼의 값을 확인
     * 사용 중인 엔티티에 boolean 값이 없어서 동작 안함
     */
//    Product findByisActiveTrue();
//    Product findByisActiveIsTrue();
//    Product findByisActiveFalse();
//    Product findByisActiveIsFalse();

    /**
     * And, Or
     */
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    /**
     * (Is)GreaterThan : >
     * (Is)LessThan : <
     * (Is)Between : 사이 값
     */
    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    /**
     * (Is)Like : 사용 시 자신이 원하는 위치에 % 를 붙여서 문자열 파라미터를 넘겨야한다.
     * StartsWith == (Is)StartingWith (== StartsWith) : 문자열의 앞에 % 배치
     * EndsWith == (Is)EndingWith : 문자열의 끝에 % 배치
     * Contains == (Is)Containing
     * 컬럼에서 일부 일치 여부를 확인 (검색 조건)
     */
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);
    
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    /**
     * OrderBy : 정렬
     * Asc : 오름차순
     * Desc : 내림차순
     */
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    /**
     * 여러 조건으로 정렬 시 And 나 Or 키워드 없이 조건을 계속 나열하면 된다.
     */
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    /**
     * Sort 객체를 활용한 정렬
     */
    List<Product> findByName(String name, Sort sort);

    /**
     * 페이징 처리
     */
    Page<Product> findByName(String name, Pageable pageable);

    /**
     * @Query : JPQL 작성
     * @Param : 쿼리 작성에 필요한 파라미터
     */
    @Query("select p from Product p where p.name = :name")
    List<Product> findByName(@Param("name") String name);

    @Query("select p.name, p.price, p.stock from Product p where p.name = :name")
    List<Tuple> findByName2(@Param("name") String name);

}
