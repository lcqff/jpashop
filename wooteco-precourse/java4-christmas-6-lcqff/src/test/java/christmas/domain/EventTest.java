package christmas.domain;

import static christmas.domain.menu.Menu.CHAMPAGNE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.PresentationEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EventTest {

    @Nested
    @DisplayName("증정품 할인 테스트")
    class GetDiscountTest {
        @Test
        void 증정품을_받을시_증정품_가격을_할인내역에_포함한다() {
            int totalPrice = 120_000;
            PresentationEvent presentationEvent = new PresentationEvent(totalPrice);
            assertEquals(CHAMPAGNE.getPrice(), presentationEvent.getDiscount());
        }
        @Test
        void 증정품을_받지_못했을_시_할인내역에_포함되지_않는다() {
            int totalPrice = 119_999;
            PresentationEvent presentationEvent = new PresentationEvent(totalPrice);
            assertEquals(0, presentationEvent.getDiscount());
        }
    }



}