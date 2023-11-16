package christmas.domain.discounts;

import christmas.domain.date.DecemberDate;

public abstract class Discount {
    int discount = 0;
    DecemberDate decemberDate;

    public int getDiscount() {
        return discount;
    }

    abstract protected void applyDiscount();

    abstract String getDiscountMessage();

    @Override
    public String toString() {
        if (discount > 0) {
            return getDiscountMessage().formatted(discount) + '\n';
        }
        return "";
    }
}
