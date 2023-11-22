package christmas.domain;

import christmas.domain.badge.Badge;

public class Customer {
    private Badge badge;
    private final OrderInfo orderInfo;

    public Customer(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void assignBadge(int totalDiscount) {
        for (Badge badge : Badge.values()) {
            if (badge.getBadge(totalDiscount) != null) {
                setBadge(badge);
                return;
            }
        }
    }

    private void setBadge(Badge badge) {
        this.badge = badge;
    }

    public String getBadgeName() {
        return badge.getBadgeName();
    }
}
