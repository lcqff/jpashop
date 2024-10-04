package abstraction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ReservationOrder의 종류가 변경되어도 DashboardHelper 클래스는 수정되지 않는다.
 */
public class DashboardHelper {
    public Integer calculateTodayRevenue(List<ReservationOrder> reservationOrders) {
        return reservationOrders.stream()
                .mapToInt(ReservationOrder::getTotalPriceOfPaidOrder)
                .sum();
    }

    public String findMostOrderedMenuName(List<ReservationOrder> reservationOrders) {
        return reservationOrders.stream()
                .filter(ReservationOrder::isMenuAndStatusConfirmed)
                .collect(Collectors.groupingBy(ReservationOrder::getMenuName, Collectors.counting())) // 메뉴 이름 별 개수
                .toString();

    }

    public Integer calculateRemainingReservationCount(List<ReservationOrder> reservationOrders) {
        return (int) reservationOrders.stream()
                .filter(ReservationOrder::isStatusConfirmedAndPaidWithoutNoShow)
                .count();
    }
}
