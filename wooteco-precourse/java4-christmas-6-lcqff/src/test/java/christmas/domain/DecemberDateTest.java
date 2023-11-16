package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.date.DecemberDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DecemberDateTest {

    @Nested
    @DisplayName("날짜 입력 예외 처리 테스트")
    class DateValidationTest {
        @ValueSource(strings = {"-10", "0", "99"})
        @ParameterizedTest
        void 날짜는_1에서_31_범위여야_한다(Integer input) {
            assertThatThrownBy(() -> new DecemberDate(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("로직 정상 동작 테스트")
    class DateTest {
        @Test
        void 유효한_입력이_들어올시_정상적으로_동작한다() {
            int date = 5;
            DecemberDate decemberDate = new DecemberDate(date);
            assertEquals(date, decemberDate.date());
        }
    }

    @Nested
    @DisplayName("주말 테스트")
    class isWeekendTest {
        @ValueSource(strings = {"3", "4", "5", "6", "7", "10"})
        @ParameterizedTest
        void 주말이_아닐시_거짓을_반환한다(int date) {
            DecemberDate decemberDate = new DecemberDate(date);
            assertFalse(decemberDate.isWeekend());
        }

        @ValueSource(strings = {"8", "9"})
        @ParameterizedTest
        void 주말일시_참을_반환한다(int date) {
            DecemberDate decemberDate = new DecemberDate(date);
            assertTrue(decemberDate.isWeekend());
        }
    }

    @Nested
    @DisplayName("크리스마스가 지났는가 테스트")
    class isBeforeChristmasTest {
        @ValueSource(strings = {"1", "24", "25"})
        @ParameterizedTest
        void 크리스마스가_지나지_않았을시_참을_반환한다(int date) {
            DecemberDate decemberDate = new DecemberDate(date);
            assertTrue(decemberDate.isBeforeChristmas());
        }

        @ValueSource(strings = {"26", "31"})
        @ParameterizedTest
        void 크리스마스가_지났을시_거짓을_반환한다(int date) {
            DecemberDate decemberDate = new DecemberDate(date);
            assertFalse(decemberDate.isWeekend());
        }
    }
}