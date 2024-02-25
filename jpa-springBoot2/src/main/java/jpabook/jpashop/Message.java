package jpabook.jpashop;


public enum Message {
    PRINT_INCOME_RATE_MESSAGE("총 수익률은 %.1f%%입니다.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}