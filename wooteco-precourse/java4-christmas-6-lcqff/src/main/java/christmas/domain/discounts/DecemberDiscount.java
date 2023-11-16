package christmas.domain.discounts;

import static christmas.domain.menu.MenuCategory.DESSERT;
import static christmas.domain.menu.MenuCategory.MAIN;

import christmas.domain.date.DecemberDate;
import christmas.domain.OrderInfo;

public class DecemberDiscount {

    private final static int MIN_DISCOUNT_PRICE = 10_000;
    private final static String NOTHING = "없음";
    private int discount = 0;
    DDayDiscount dDayDiscount;
    WeekdayDiscount weekdayDiscount;
    WeekendDiscount weekendDiscount;
    SpecialDiscount specialDiscount;

    public DecemberDiscount(OrderInfo orderInfo) {
        DecemberDate date = orderInfo.getDate();
        int dessertCount = orderInfo.countCategory(DESSERT);
        int mainCount = orderInfo.countCategory(MAIN);
        int totalPrice = orderInfo.getTotalPrice();
        if (applyDecemberDiscount(totalPrice)) {
            initDiscounts(date, dessertCount, mainCount);
            this.discount = dDayDiscount.getDiscount() + weekendDiscount.getDiscount()
                    + weekdayDiscount.getDiscount() + specialDiscount.getDiscount();
        }
    }

    private void initDiscounts(DecemberDate date, int dessertCount, int mainCount) {
        dDayDiscount = new DDayDiscount(date);
        weekendDiscount = new WeekendDiscount(date, mainCount);
        weekdayDiscount = new WeekdayDiscount(date, dessertCount);
        specialDiscount = new SpecialDiscount(date);
    }

    private boolean applyDecemberDiscount(int totalPrice) {
        return totalPrice >= MIN_DISCOUNT_PRICE;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return dDayDiscount.toString() +
                    weekdayDiscount.toString() +
                    weekendDiscount.toString() +
                    specialDiscount.toString();
        }
        return NOTHING;
    }
}
