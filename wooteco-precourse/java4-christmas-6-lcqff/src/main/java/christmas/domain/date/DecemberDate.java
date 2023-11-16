package christmas.domain.date;

import static christmas.domain.date.DecemberDay.*;
import static christmas.utils.ErrorMessage.OUT_OF_RANGE_DATE_ERROR;

import christmas.utils.ChristmasException;

public record DecemberDate(int date) {
    private final static int FIRST_DATE = 1;
    private final static int LAST_DATE = 31;
    private final static int WEEK_DAYS = 7;

    public DecemberDate {
        isValidRange(date);
    }

    private void isValidRange(int date) {
        if (date < FIRST_DATE || date > LAST_DATE) {
            throw new ChristmasException(OUT_OF_RANGE_DATE_ERROR);
        }
    }

    public boolean isBeforeChristmas() {
        return date <= CHRISTMAS.getDate();
    }

    public boolean isWeekend() {
        int firstDayDate = date % WEEK_DAYS;
        return firstDayDate == FRIDAY.getDate() || firstDayDate == SATURDAY.getDate();
    }
}
