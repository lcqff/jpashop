package christmas.domain;

import static christmas.domain.menu.Menu.*;
import static christmas.domain.menu.MenuCategory.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.date.DecemberDate;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderInfoTest {

    DecemberDate date = new DecemberDate(5);

    @Nested
    @DisplayName("주문 예외 처리")
    class OrderInfoValidateTest {
        HashMap<Menu, Integer> menu = new HashMap<>();

        @Test
        void 음료만_주문할_수_없다() {
            menu.put(Menu.ZERO_COKE, 2);
            menu.put(Menu.CHAMPAGNE, 3);
            assertThatThrownBy(() -> new OrderInfo(date, menu)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 한번에_20개를_초과해_주문할_수_없다() {
            menu.put(Menu.T_BONE_STEAK, 21);
            assertThatThrownBy(() -> new OrderInfo(date, menu)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("특정 카테고리에 속하는 메뉴 개수를 카운트")
    class CountCategoryTest {
        HashMap<Menu, Integer> menu = new HashMap<>() {{
            put(ICE_CREAM, 1);
            put(CHOCOLATE_CAKE, 2);
            put(MUSHROOM_SOUP, 1);
            put(CAESAR_SALAD, 2);
            put(ZERO_COKE, 1);
            put(CHAMPAGNE, 2);
            put(T_BONE_STEAK, 1);
            put(SEAFOOD_PASTA, 2);
        }};
        OrderInfo orderInfo = new OrderInfo(date, menu);

        @Test
        void 디져트_카테고리에_속하는_메뉴의_수를_반환한다() {
            assertEquals(3, orderInfo.countCategory(DESSERT));
        }

        @Test
        void 메인_카테고리에_속하는_메뉴의_수를_반환한다() {
            assertEquals(3, orderInfo.countCategory(MAIN));
        }

        @Test
        void 에피타이져_카테고리에_속하는_메뉴의_수를_반환한다() {
            assertEquals(3, orderInfo.countCategory(APPETIZER));
        }

        @Test
        void 음료_카테고리에_속하는_메뉴의_수를_반환한다() {
            assertEquals(3, orderInfo.countCategory(DRINK));
        }
    }

    @Nested
    @DisplayName("전체 가격 계산 테스트")
    class CalculateTotalPriceTest {
        @Test
        void 주문_메뉴의_총금액을_정확하게_계산해야한다() {
            HashMap<Menu, Integer> menu = new HashMap<>() {{
                put(ICE_CREAM, 1); //5,000
                put(MUSHROOM_SOUP, 1); //6,000
                put(ZERO_COKE, 1); //3,000
                put(T_BONE_STEAK, 1); //55,000
            }};
            int totalPrice = 5000 + 6000 + 3000 + 55000;
            OrderInfo orderInfo = new OrderInfo(date, menu);
            assertEquals(totalPrice, orderInfo.getTotalPrice());

        }
    }
}
