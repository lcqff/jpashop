package christmas.domain.event;

import static christmas.domain.menu.Menu.CHAMPAGNE;

public class PresentationEvent {
    private final static String PRESENTATION_DISCOUNT_MESSAGE = "증정 이벤트: -25,000원";
    private final static String PRESENT_CHAMPAGNE = "샴페인 1개";
    private final static String NOTHING = "없음";
    private final static int PRESENTATION_PRICE = 120_000;
    private final boolean getPresent;

    public PresentationEvent(int totalPrice) {
        this.getPresent = doesGetPresent(totalPrice);
    }

    private boolean doesGetPresent(int totalPrice) {
        return totalPrice >= PRESENTATION_PRICE;
    }

    public int getDiscount() {
        if (getPresent) {
            return CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public String getPresentationMessage() {
        if (getPresent) {
            return PRESENT_CHAMPAGNE;
        }
        return NOTHING;
    }

    @Override
    public String toString() {
        if (getPresent) {
            return PRESENTATION_DISCOUNT_MESSAGE;
        }
        return "";
    }
}
