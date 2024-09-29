package encapsulation;

public class LawOfDemeter {
    /**
     * - 메서드에서 생성한 객체의 메서드만 호출한다.
     * - 파라미터로 받은 객체의 메서드만 호출한다.
     * - 필드로 참조하는 객체의 메서드만 호출한다.
     */

    private void bad() {
        Wallet wallet = customer.getWallet(); // 참고하고 있는 Customer 객체의 메서드를 호출해 Wallet 객체를 받아온다.
        if (wallet.getTotalMoney() >= payment) { // Wallet 객체의 메서드를 호출한다.
            wallet.substractMoney(payment);
        }
    }

    private void good() {
        int paidAmount = customer.getPayment(payment); // 참고하고 있는 Customer 메서드만을 호출한다.
    }
}

