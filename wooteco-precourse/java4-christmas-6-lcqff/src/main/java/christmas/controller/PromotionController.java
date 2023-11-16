package christmas.controller;

import christmas.service.ChristmasPromotion;
import christmas.domain.Customer;
import christmas.domain.date.DecemberDate;
import christmas.domain.menu.Menu;
import christmas.domain.OrderInfo;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        OrderInfo orderInfo = inputOrderInfo();
        Customer customer = new Customer(orderInfo);
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(customer,orderInfo);
        outputView.printChristmasPromotion(christmasPromotion);
    }

    private OrderInfo inputOrderInfo() {
        outputView.printHelloMessage();
        DecemberDate visitDate = inputView.inputDate();
        HashMap<Menu, Integer> menu = inputView.inputMenu();

        return new OrderInfo(visitDate, menu);
    }


}
