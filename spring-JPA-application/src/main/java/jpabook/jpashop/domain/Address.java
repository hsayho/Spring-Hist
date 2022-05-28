package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

            // 이러한 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플렉션 같은 기술을 사용있도록 지원해야 하기 때문이다.
@Embeddable // 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정을 해둬야 한다.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    protected Address() {

    }
}
