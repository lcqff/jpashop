package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.util.List;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.items.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = Member.builder()
                    .name("member a")
                    .address(new Address("부산", "남구", "1111"))
                    .build();
            em.persist(member);

            Book book1 = Book.builder()
                    .price(1000)
                    .stock(100)
                    .name("변신")
                    .build();

            Book book2 = Book.builder()
                    .price(2000)
                    .stock(200)
                    .name("어린왕자")
                    .build();
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 10);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 5);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = Member.builder()
                    .name("member b")
                    .address(new Address("진주", "북구", "2222"))
                    .build();
            em.persist(member);

            Book book1 = Book.builder()
                    .price(1000)
                    .stock(100)
                    .name("소공녀")
                    .build();

            Book book2 = Book.builder()
                    .price(2000)
                    .stock(200)
                    .name("로빈슨 크루소")
                    .build();
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 10);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 5);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}


