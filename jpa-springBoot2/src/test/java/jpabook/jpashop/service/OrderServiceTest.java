package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.items.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    Member member = new Member();
    Book book = new Book();

    @BeforeEach
    void setup() {
        member.setName("길동");
        member.setAddress(new Address("서울", "경기", "123"));
        em.persist(member);

        book.setName("변신");
        book.setPrice(10_000);
        book.setStockQuantity(10);
        em.persist(book);
    }

    @Test
    void 상품주문() {
        Long orderId = orderService.order(member.getId(), book.getId(), 3);
        Order getOrder = orderRepository.find(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1,getOrder.getOrderItems().size());
        assertEquals(10000*3, getOrder.getTotalPrice());
        assertEquals(7, book.getStockQuantity());
        assertEquals(orderId, getOrder.getId());

    }

    @Test
    void 상품주문_재고수량초과() {
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), 11));
    }

    @Test
    void 상품취소() {
        Long orderId = orderService.order(member.getId(),book.getId(),2);
        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.find(orderId);
        assertEquals(OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals(10,book.getStockQuantity());

    }

}