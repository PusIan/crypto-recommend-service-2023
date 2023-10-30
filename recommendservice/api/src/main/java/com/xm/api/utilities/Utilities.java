package com.xm.api.utilities;

import java.time.LocalDate;

public class Utilities {
    public static LocalDate truncateDateToMonth(LocalDate dt) {
        return LocalDate.of(dt.getYear(), dt.getMonth(), 1);
    }

    public static LocalDate fillDateToMonthIfNull(LocalDate dt) {
        dt = dt == null ? LocalDate.now() : dt;
        dt = Utilities.truncateDateToMonth(dt);
        return dt;
    }
}
