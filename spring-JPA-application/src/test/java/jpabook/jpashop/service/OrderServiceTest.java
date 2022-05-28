package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("황세호");
        member.setAddress(new Address("성남시", "고등로", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("JPA 활용 책");
        book.setAuthor("김영한");
        book.setPrice(30000);
        book.setStockQuantity(10);
        em.persist(book);
        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order result = orderRepository.findOne(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(result.getStatus());
        assertThat(result.getTotalPrice()).isEqualTo(orderCount*book.getPrice());
        assertThat(book.getStockQuantity()).isEqualTo(8);
        //assertEquals("상품 주문 후 상태는 ORDER", OrderStatus.ORDER, result.getStatus());
    }

    @Test
    public void 주취소() throws Exception{
        //given
        Member member = new Member();
        member.setName("황세호");
        member.setAddress(new Address("성남시", "고등로", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("JPA 활용 책");
        book.setAuthor("김영한");
        book.setPrice(30000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 10;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(0);

        //when
        orderService.cancelOrder(orderId);


        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    @Test
    public void 상품주문_재고초과() throws Exception{
        //given
        Member member = new Member();
        member.setName("황세호");
        member.setAddress(new Address("성남시", "고등로", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("JPA 활용 책");
        book.setAuthor("김영한");
        book.setPrice(30000);
        book.setStockQuantity(10);
        em.persist(book);

        //when
        int orderCount = 11;

        //then
        try{
            Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        }catch(NotEnoughStockException e){
            return;
        }
        fail();
    }
}