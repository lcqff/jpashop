package jpabook.jpashop.api;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderSimpleController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> getOrdersV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        for (Order order : orders) {
            order.getMember().getName();
            //Lazy 강제 초기화  -> @JsonIgnore을 통해 Member ignore 해줌 -> null로 받아와지겠지만 name과 address는 강제 초기화 되어 실제 값 받아와짐?
            order.getDelivery().getAddress();
        }
        return orders;
    }

    @GetMapping("/api/v2/simple-orders")
    public Result getOrdersV2() {
        //1 + N
        //order 1 + member N + delivery N
        //1 + 2 + 2*(1+1)
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        System.out.println("================================");
        List<SimpleOrderData> orderDatas = orders.stream()
                .map(order -> new SimpleOrderData(
                        order.getId(),
                        order.getMember().getName(), //Lazy 초기화
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getDelivery().getAddress()) //Lazy 초기화는 되는데 Order를 또 가져옴(Lazy 처리가 돼있음에도)
                ).toList();
        return new Result(orderDatas);
    }

    @Data
    @AllArgsConstructor
    static class SimpleOrderData {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        T data;
    }
}
