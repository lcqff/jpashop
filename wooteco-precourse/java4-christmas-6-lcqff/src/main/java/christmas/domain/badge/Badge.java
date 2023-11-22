package christmas.domain.badge;

public enum Badge {
    NONE("없음", totalDiscount -> totalDiscount < 5_000),
    STAR("별", totalDiscount -> totalDiscount < 10_000 && totalDiscount >= 5_000),
    TREE("트리", totalDiscount -> totalDiscount < 20_000 && totalDiscount >= 10_000),
    SANTA("산타", totalDiscount -> totalDiscount >= 20_000);

    private final String badgeName;
    private final Badge.BadgeAssignment assignBadge;

    Badge(String badgeName, Badge.BadgeAssignment assignBadge) {
        this.badgeName = badgeName;
        this.assignBadge = assignBadge;
    }

    public Badge getBadge(int totalDiscount) {
        if (assignBadge.assignBadge(totalDiscount)) {
            return this;
        }
        return null;
    }

    public String getBadgeName() {
        return badgeName;
    }

    interface BadgeAssignment {
        boolean assignBadge(int totalDiscount);
    }
}
