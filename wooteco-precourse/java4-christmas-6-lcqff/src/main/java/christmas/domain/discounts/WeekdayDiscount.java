package christmas.domain.discounts;

import christmas.domain.date.DecemberDate;

public class WeekdayDiscount extends Discount {
    private static final int DESSERT_DISCOUNT = 2023;
    private static final String WEEKDAY_DISCOUNT_MESSAGE = "평일 할인: -%,d원";
    int categoryCount;


    public WeekdayDiscount(DecemberDate decemberDate, int dessertCount) {
        this.decemberDate = decemberDate;
        this.categoryCount = dessertCount;
        applyDiscount();
    }

    @Override
    public void applyDiscount() {
        if (!decemberDate.isWeekend()) {
            this.discount += categoryCount * DESSERT_DISCOUNT;
        }
    }

    @Override
    public String getDiscountMessage() {
        return WEEKDAY_DISCOUNT_MESSAGE;
    }
}
