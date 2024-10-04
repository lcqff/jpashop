package abstraction;

public interface ReservationOrder {
    Integer getTotalPriceOfPaidOrder();

    Boolean isMenuAndStatusConfirmed();

    Boolean isStatusConfirmedAndPaidWithoutNoShow();

    String getMenuName();
}
