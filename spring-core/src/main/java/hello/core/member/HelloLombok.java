package hello.core.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private int age;
    private String name;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("lombok");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
