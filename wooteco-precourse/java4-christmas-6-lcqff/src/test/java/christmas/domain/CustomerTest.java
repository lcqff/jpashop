package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Nested
    @DisplayName("배지 할당 테스트")
    class AssignBadgeTest {
        OrderInfo orderInfo = mock(OrderInfo.class);
        Customer customer = new Customer(orderInfo);
        @Test
        void 별_배지를_할당받는다() {
            customer.assignBadge(5000);
            assertEquals("별",customer.getBadgeName());
        }
        @Test
        void 트리_배지를_할당받는다() {
            customer.assignBadge(10000);
            assertEquals("트리",customer.getBadgeName());
        }
        @Test
        void 산타_배지를_할당받는다() {
            customer.assignBadge(20000);
            assertEquals("산타",customer.getBadgeName());
        }
        @Test
        void 배지를_할당받지_못한다() {
            customer.assignBadge(4999);
            assertEquals("없음",customer.getBadgeName());
        }


    }

}