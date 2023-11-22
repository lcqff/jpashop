package christmas.domain.discounts;

import christmas.domain.date.DecemberDate;

public class WeekendDiscount extends Discount {
    private static final int MAIN_DISCOUNT = 2023;
    private static final String WEEKEND_DISCOUNT_MESSAGE = "주말 할인: -%,d원";
    int categoryCount;

    public WeekendDiscount(DecemberDate decemberDate, int mainCount) {
        this.decemberDate = decemberDate;
        this.categoryCount = mainCount;
        applyDiscount();
    }

    @Override
    public void applyDiscount() {
        if (decemberDate.isWeekend()) {
            this.discount = categoryCount * MAIN_DISCOUNT;
        }
    }

    @Override
    public String getDiscountMessage() {
        return WEEKEND_DISCOUNT_MESSAGE;
    }

}
