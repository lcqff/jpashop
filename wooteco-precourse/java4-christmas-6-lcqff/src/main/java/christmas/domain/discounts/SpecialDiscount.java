package christmas.domain.discounts;

import static christmas.domain.date.DecemberDay.SUNDAY;
import static christmas.domain.date.DecemberDay.CHRISTMAS;

import christmas.domain.date.DecemberDate;

public class SpecialDiscount extends Discount {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final String SPECIAL_DISCOUNT_MESSAGE = "특별 할인: -%,d원";

    public SpecialDiscount(DecemberDate decemberDate) {
        this.decemberDate = decemberDate;
        applyDiscount();
    }

    @Override
    public void applyDiscount() {
        int firstDayDate = decemberDate.date() % 7;
        if (firstDayDate == SUNDAY.getDate() || decemberDate.date() == CHRISTMAS.getDate()) {
            discount = SPECIAL_DISCOUNT;
        }
    }

    @Override
    public String getDiscountMessage() {
        return SPECIAL_DISCOUNT_MESSAGE;
    }
}
