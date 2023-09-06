package com.springboot.relationship.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "provider_id")
    private Long id;
    private String name;

    /**
     * 양방향 연관관계 시 주인이 아닌 쪽은 mappedBy를 통해 주인쪽 엔티티를 읽기만 가능하다.
     *
     * fetch = FetchType.EAGER : 다른 엔티티 테이블에 접근해 데이터를 가져오는 것을 즉시 로딩으로 설정
     *
     * cascade = CascadeType.PERSIST : 영속성 전이를 Persist 로 설정
     * -> 자식 엔티티를 insert 하거나 update 시 부모 테이블의 컬럼도 함께 변한다.
     *
     * orphanRemoval = true : 부모 객체가 DB 에서 삭제되면 해당 객체도 함께 삭제
     */
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
