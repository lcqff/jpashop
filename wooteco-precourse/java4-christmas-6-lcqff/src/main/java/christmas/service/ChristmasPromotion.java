package christmas.service;

import christmas.domain.OrderInfo;
import christmas.domain.discounts.DecemberDiscount;
import christmas.domain.Customer;
import christmas.domain.event.PresentationEvent;

public class ChristmasPromotion {
    private static final String PROMOTION_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private final static String PRESENTATION_MESSAGE = "<증정 메뉴>";
    private final static String DISCOUNT_DETAIL_MESSAGE = "<혜택 내역>";
    private final static String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private final static String DISCOUNTED_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private final static String BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String PRICE = "%,d원\n";

    DecemberDiscount decemberDiscount;
    PresentationEvent presentationEvent;
    OrderInfo orderInfo;
    Customer customer;
    int totalDiscount;

    public ChristmasPromotion(Customer customer, OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
        this.decemberDiscount = new DecemberDiscount(orderInfo);
        this.presentationEvent = new PresentationEvent(orderInfo.getTotalPrice());
        this.totalDiscount = calculateTotalDiscount();
        this.customer = customer;
        customer.assignBadge(totalDiscount);
    }

    private int calculateTotalDiscount() {
        return decemberDiscount.getDiscount() + presentationEvent.getDiscount();
    }

    private String getDiscountDetail() {
        return decemberDiscount.toString() + presentationEvent.toString();
    }

    private int calculateDiscountedPrice() {
        return orderInfo.getTotalPrice() - totalDiscount;
    }

    @Override
    public String toString() {
        return PROMOTION_PREVIEW_MESSAGE.formatted(orderInfo.getDate().date()) + "\n" +
                ORDERED_MENU_MESSAGE + "\n" + orderInfo.toString() +
                TOTAL_PRICE_MESSAGE + "\n" + PRICE.formatted(orderInfo.getTotalPrice()) +
                PRESENTATION_MESSAGE + "\n" + presentationEvent.getPresentationMessage() + "\n" +
                DISCOUNT_DETAIL_MESSAGE + "\n" + getDiscountDetail() + "\n" +
                TOTAL_DISCOUNT_MESSAGE + "\n" + PRICE.formatted(-1 * totalDiscount) + "\n" +
                DISCOUNTED_PRICE_MESSAGE + "\n" + PRICE.formatted(calculateDiscountedPrice()) + "\n" +
                BADGE_MESSAGE + "\n" + customer.getBadgeName();
    }
}
