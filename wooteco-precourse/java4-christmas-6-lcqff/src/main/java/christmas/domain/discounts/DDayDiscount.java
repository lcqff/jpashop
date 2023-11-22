package christmas.domain.discounts;

import christmas.domain.date.DecemberDate;

public class DDayDiscount extends Discount{
    private static final int D_DAY_DISCOUNT = 100;
    private static final int D_DAY_DISCOUNT_START = 1000;
    private static final String D_DAY_DISCOUNT_MESSAGE = "크리스마스 디데이 할인: -%,d원";

    public DDayDiscount(DecemberDate decemberDate) {
        this.decemberDate = decemberDate;
        applyDiscount();
    }

    @Override
    public void applyDiscount() {
        if (decemberDate.isBeforeChristmas()) {
            discount = D_DAY_DISCOUNT_START + (decemberDate.date()-1)*D_DAY_DISCOUNT;
        }
    }

    @Override
    public String getDiscountMessage() {
        return D_DAY_DISCOUNT_MESSAGE;
    }
}
