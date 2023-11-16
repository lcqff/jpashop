package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.date.DecemberDate;
import christmas.domain.menu.MenuCategory;
import christmas.domain.discounts.DDayDiscount;
import christmas.domain.discounts.DecemberDiscount;
import christmas.domain.discounts.SpecialDiscount;
import christmas.domain.discounts.WeekdayDiscount;
import christmas.domain.discounts.WeekendDiscount;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountTest {

    @Nested
    @DisplayName("할인 테스트")
    class DecemberDiscountTest {
        OrderInfo orderInfo = mock(OrderInfo.class);
        final static int dessertCount = 3;
        final static int mainCount = 3;
        final static int weekdiscount = 2023;

        @BeforeEach
        void beforeSetup() {
            when(orderInfo.countCategory(MenuCategory.DESSERT)).thenReturn(dessertCount);
            when(orderInfo.countCategory(MenuCategory.MAIN)).thenReturn(mainCount);
        }

        @MethodSource("customerAndDiscount")
        @ParameterizedTest(name = "날짜 : {0}, 결과 :{1}")
        void 총_할인_금액을_정확하게_계산해야한다(Integer date, Integer result) {
            when(orderInfo.getTotalPrice()).thenReturn(100_000);
            when(orderInfo.getDate()).thenReturn(new DecemberDate(date));
            DecemberDiscount decemberDiscount = new DecemberDiscount(orderInfo);
            assertEquals(result, decemberDiscount.getDiscount());
        }

        @Test
        void 총금액이_10000원_미만인_경우_할인이_적용되지_않는다() {
            when(orderInfo.getTotalPrice()).thenReturn(9999);
            when(orderInfo.getDate()).thenReturn(new DecemberDate(5));
            DecemberDiscount decemberDiscount = new DecemberDiscount(orderInfo);
            assertEquals(0, decemberDiscount.getDiscount());
        }

        static Stream<Arguments> customerAndDiscount() {
            return Stream.of(
                    Arguments.arguments(26, dessertCount * weekdiscount), //주중
                    Arguments.arguments(29, mainCount * weekdiscount), //주말
                    Arguments.arguments(2, 1100 + mainCount * weekdiscount), //디데이, 주말
                    Arguments.arguments(4, 1300 + dessertCount * weekdiscount), //디데이, 주중
                    Arguments.arguments(31, dessertCount * weekdiscount + 1000), //스페셜, 주중
                    Arguments.arguments(3, 1200 + dessertCount * weekdiscount + 1000) //디데이,스페셜,주중
            );
        }

    }

    @Nested
    @DisplayName("디데이 할인 테스트")
    class DDayDiscountTest {
        @Test
        void 크리스마스_할인이_정상적으로_계산된다() {
            DecemberDate beforeChristmas = new DecemberDate(25);
            DDayDiscount dDayDiscount = new DDayDiscount(beforeChristmas);
            assertEquals(3400, dDayDiscount.getDiscount());
        }

        @Test
        void 크리스마스가_지나면_크리스마스_할인이_적용되지_않는다() {
            DecemberDate afterChristmas = new DecemberDate(26);
            DDayDiscount dDayDiscount = new DDayDiscount(afterChristmas);
            assertEquals(0, dDayDiscount.getDiscount());
        }
    }

    @Nested
    @DisplayName("특별 할인 테스트")
    class SpecialDiscountTest {
        @ValueSource(ints = {25, 31})
        @ParameterizedTest
        void 특별할인이_정상적으로_적용된다(Integer input) {
            DecemberDate date = new DecemberDate(input);
            SpecialDiscount specialDiscount = new SpecialDiscount(date);
            assertEquals(1000, specialDiscount.getDiscount());
        }

        @Test
        void 특별할인이_적용되지_않는_날엔_적용되지_않는다() {
            DecemberDate notSpecialDay = new DecemberDate(18);
            SpecialDiscount specialDiscount = new SpecialDiscount(notSpecialDay);
            assertEquals(0, specialDiscount.getDiscount());
        }
    }

    @Nested
    @DisplayName("주말 할인 테스트")
    class WeekendDiscountTest {
        int mainCount = 3;

        @ValueSource(ints = {1, 2})
        @ParameterizedTest
        void 주말에_주말할인이_정상적으로_적용된다(Integer input) {
            DecemberDate decemberDate = new DecemberDate(input);
            WeekendDiscount weekendDiscount = new WeekendDiscount(decemberDate, mainCount);
            assertEquals(mainCount * 2023, weekendDiscount.getDiscount());
        }

        @ValueSource(ints = {3, 4, 5, 6, 7})
        @ParameterizedTest
        void 주일엔_주말할인이_적용되지_않는다(Integer input) {
            DecemberDate decemberDate = new DecemberDate(input);
            WeekendDiscount weekendDiscount = new WeekendDiscount(decemberDate, mainCount);
            assertEquals(0, weekendDiscount.getDiscount());
        }
    }

    @Nested
    @DisplayName("주일 할인 테스트")
    class WeekDayDiscountTest {
        int dessertCount = 3;

        @ValueSource(ints = {3, 4, 5, 6, 7})
        @ParameterizedTest
        void 주일에_주일할인이_정상적으로_적용된다(Integer input) {
            DecemberDate decemberDate = new DecemberDate(input);
            WeekdayDiscount weekdayDiscount = new WeekdayDiscount(decemberDate, dessertCount);
            assertEquals(dessertCount * 2023, weekdayDiscount.getDiscount());
        }

        @ValueSource(ints = {1, 2})
        @ParameterizedTest
        void 주일엔_주말할인이_적용되지_않는다(Integer input) {
            DecemberDate decemberDate = new DecemberDate(input);
            int dessertCount = 3;
            WeekdayDiscount weekdayDiscount = new WeekdayDiscount(decemberDate, dessertCount);
            assertEquals(0, weekdayDiscount.getDiscount());
        }
    }

}