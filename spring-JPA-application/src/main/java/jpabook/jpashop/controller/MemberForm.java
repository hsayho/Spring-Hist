package jpabook.jpashop.controller;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Entity는 실제 채워야하는 form과 속성이 다르므로
 * Entity의 유지보수성을 위해 비즈니스 로직이 아닌 것을 Entity가 아닌 MemberForm을 사용
 */

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
