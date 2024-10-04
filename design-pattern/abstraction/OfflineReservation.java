package abstraction;

public class OfflineReservation implements ReservationOrder {
    @Override
    public Integer getTotalPriceOfPaidOrder() {
        return null;
    }

    @Override
    public Boolean isMenuAndStatusConfirmed() {
        return null;
    }

    @Override
    public Boolean isStatusConfirmedAndPaidWithoutNoShow() {
        return null;
    }

    @Override
    public String getMenuName() {
        return null;
    }
}
